# 选课与考勤管理系统 —— 代码审查报告

这份报告对你在三年前（大二时期）编写的 Java + 数据库大作业进行了一次中肯、客观的 Code Review。

整体来看，作为一个大二学生的期末大作业，这个项目**功能完整、结构清晰**（分包明确：`dao`、`model`、`util`、`view`），具备了选课、考勤、请假以及角色权限控制等核心业务逻辑，完成度很高。

但在工业级标准或高质量代码的视角下，代码中存在不少**严重的安全隐患、资源泄露和架构设计问题**。

以下是具体的审查细节和中肯评价：

---

## 📊 综合评分与总结

*   **大二大作业标准**：**8.5 / 10**（功能完整，分包明确，有完整的业务闭环，自定义异常和权限逻辑表现优异）
*   **工业级/生产标准**：**3.0 / 10**（存在多处严重的连接泄露、SQL注入隐患，多线程与组件使用不当，无法在高并发或生产环境下稳定运行）

---

## 🔴 致命缺陷（Critical Issues）

这些问题如果在生产环境中运行，会导致系统迅速崩溃、数据损坏或遭遇黑客攻击。

### 1. 严重的数据库连接泄露（Connection Leaks）
这是本项目最严重的 Bug。在很多 View 类和部分 DAO 方法中，数据库连接 `Connection` 被开启，但**在任何路径下都没有关闭**。
*   **典型案例**：[OneClickAttendanceFrm.java](file:///d:/Data/classSchedule/src/cn/edu/hzcu/ky/view/OneClickAttendanceFrm.java) 中的 `clickActionPerfomed` 方法。
    *   在第 128 行：`Connection conn = DBUtil.getConnection();`（查询学期，**未关闭**）
    *   在第 173 行：`Connection conn = DBUtil.getConnection();`（查询学生课程，**未关闭**）
    *   在第 267 行：`Connection conn = DBUtil.getConnection();`（录入考勤，**未关闭**）
*   **后果**：每当学生点击一次“一键签到”按钮，就会永久泄露 2 到 3 个数据库连接。MySQL 默认的最大连接数是 151，这意味着**只要有几个学生点几下，整个系统的数据库就会瘫痪**，提示 `Too many connections`。
*   **DAO 层类似问题**：[StudentDao.java](file:///d:/Data/classSchedule/src/cn/edu/hzcu/ky/dao/StudentDao.java) 中的 `searchSpecStudent` 方法，获取了连接但从未关闭。

### 2. 存在 SQL 注入隐患（SQL Injection）
在进行模糊查询或动态条件拼接时，使用了直接拼接字符串的方式，而不是占位符 `?`。
*   **典型案例**：[StudentDao.java](file:///d:/Data/classSchedule/src/cn/edu/hzcu/ky/dao/StudentDao.java) 中的 `list` 方法（第 38-67 行）：
    ```java
    if(student!=null && !StringUtil.isEmpty(student.getStudentID())){
        sb.append(" and StudentID like '%"+student.getStudentID()+"%'");
    }
    ```
*   **后果**：如果用户在学生 ID 输入框中输入 `' OR '1'='1`，就能绕过查询逻辑或者窃取全部学生数据。

### 3. 破坏标准 SQL 语法的非聚合查询
在多个 SQL 查询中，使用了 `COUNT(*)` 聚合函数，但在 `SELECT` 中包含了其他非聚合列（如 `Semester`, `CourseID` 等），且**没有使用 `GROUP BY`**，或者 `GROUP BY` 的列不全。
*   **典型案例 1**：[ClassDao.java](file:///d:/Data/classSchedule/src/cn/edu/hzcu/ky/dao/ClassDao.java) 的 `loadYourAllClass`（第 91-113 行）。
*   **典型案例 2**：[OneClickAttendanceFrm.java](file:///d:/Data/classSchedule/src/cn/edu/hzcu/ky/view/OneClickAttendanceFrm.java) 的 `clickActionPerfomed` 里的主查询（第 174-212 行）。
*   **后果**：在旧版 MySQL 中该语法可以运行（但返回的非聚合列数据是随机的、不准确的）。但在现代 MySQL（5.7 及以上版本）默认开启 `ONLY_FULL_GROUP_BY` 的情况下，**这些 SQL 会直接报错**，导致程序崩溃。

---

## 🟡 架构与设计缺陷（Architectural & Design Issues）

### 1. Swing UI 线程阻塞（EDT Block）
在界面事件响应方法（如点击登录、一键签到等）中，直接在**事件分发线程（Event Dispatch Thread, EDT）**中执行数据库查询。
*   **后果**：如果数据库网络稍有延迟，整个客户端界面就会直接卡死（显示为“未响应”），直到数据库查询结束。专业做法是使用 `SwingWorker` 或另开线程执行耗时操作。

### 2. 界面容器组件的奇特误用
在 [MainFrm.java](file:///d:/Data/classSchedule/src/cn/edu/hzcu/ky/view/MainFrm.java) 中，为了承载子窗口（`JInternalFrame`），你声明并实例化了一个 `JTable`：
```java
private JTable table;
...
table = new JTable();
contentPane.add(table);
...
table.add(courseFrm); // 将子窗口添加到 JTable 中
```
*   **分析**：这是一个非常让人忍俊不禁的“野路子”写法。`JTable` 是用来展示表格数据的，尽管它继承自 `Container` 从而允许你 `add` 其他组件，但它绝不是用来做多文档界面（MDI）容器的。标准的做法是使用 `JDesktopPane`。

### 3. 全局静态状态传递与紧耦合
在 [LoginOnFrm.java](file:///d:/Data/classSchedule/src/cn/edu/hzcu/ky/view/LoginOnFrm.java) 中，使用了 `public static String userid;` 和 `public static boolean isAdmin;` 来在窗口之间共享登录状态。
*   **分析**：这是典型的面向过程思维。全局静态变量破坏了类的封装性，导致 `LoginOnFrm` 和 `MainFrm` 强耦合，如果多个用户同时在系统内登录（或者进行多用户测试），状态会被互相覆盖。

### 4. 资源清理设计不安全（未采用 Try-with-Resources）
在 DAO 层（如 [CourseDao.java](file:///d:/Data/classSchedule/src/cn/edu/hzcu/ky/dao/CourseDao.java)），即使你写了 `finally` 块来关闭连接，但在中间抛出自定义异常时，仍然漏掉了对 `PreparedStatement` 和 `ResultSet` 的关闭。
*   **典型问题**：在 Java 7 之后，已经普及了 `try-with-resources` 语法，可以自动、安全地关闭所有实现了 `AutoCloseable` 的资源，但本项目中全部使用的是手动的、容易遗漏的传统 `try-catch-finally`。
*   **提前 Return 导致的资源泄露**：在 [ClassDao.java](file:///d:/Data/classSchedule/src/cn/edu/hzcu/ky/dao/ClassDao.java) 的 `addCourseRegistration` 方法（第 287 行和 290 行）中，直接执行了 `return id;`。这导致后面的 `rs.close()` 和 `checkPst.close()` **永远无法被执行**。虽然底层 Connection 会关闭，但这种代码逻辑在逻辑上是不严谨的。

---

## 🟢 值得肯定的亮点（Strengths）

为了客观，也必须看到你在大二时展现出的良好编程意识：

1.  **异常架构设计（Exception Hierarchy）**：你设计了 `BaseException` 和 `DbException`。在 DAO 层捕获 `SQLException` 后将其包装为自定义的业务异常抛给 View 层处理。这是一个非常高级的架构意识，实现了持久层与表现层的解耦。
2.  **细致的业务权限控制**：在 `MainFrm` 中，你根据 `isAdmin` 的状态，非常细致地对菜单项进行了可见性（`setVisible(false)`）控制，让学生和管理员看到不同的菜单，符合最小权限原则。
3.  **代码分包与层次感**：
    *   `model` 存储实体。
    *   `dao` 隔离 SQL 细节。
    *   `util` 封装底层工具。
    *   `view` 编写 Swing 界面。
    这是标准的 MVC/三层架构思想的体现，大二能遵守得这么好，说明当时基础理论学得很扎实。

---

## 🔵 规范与代码风格（Code Style & Smells）

这些虽然不影响运行，但显得很不专业：

1.  **类名/属性命名不规范**：
    *   Java 实体类属性通常使用小驼峰（`studentId`, `phoneNumber`），而你使用了大驼峰（`StudentID`, `PhoneNumber`）。
    *   实体类前冠以 `Bean`（如 `BeanStudent`），这在现代 Java 开发中较为累赘，直接使用 `Student` 或 `StudentVO` 即可。
2.  **关键字冲突的妥协**：
    *   因为数据库里有 `Class` 字段，你直接在实体类中声明了 `private String Class;`。因为 `class` 是 Java 关键字，你被迫将 getter/setter 命名为 `getClass1()` / `setClass1()`。
    *   *专业做法*：在 Java 中命名为 `clazz` 或 `className`，在 SQL 映射中做对应转换，避免与关键字和 `Object.getClass()` 产生混淆。
3.  **敏感信息未脱敏与硬编码**：
    *   在 [DBUtil.java](file:///d:/Data/classSchedule/src/cn/edu/hzcu/ky/util/DBUtil.java) 中硬编码了数据库的用户名和密码（`root` / `123456`）。
    *   直接以明文 String 的方式从 `JPasswordField` 中提取密码并传输，存在内存安全隐患。
4.  **无意义的代码残留与空 Catch**：
    *   代码中留有很多注释掉的 `main` 测试方法。
    *   存在空的 `catch (Exception e) {}`，这会让调试变得极其困难（发生错误时程序没有任何反应，悄悄失败）。

---

## 💡 寄语：三年的成长

看着三年前的代码，可能你会觉得有些“黑历史”或者稚嫩，但请不要介意！
几乎所有程序员都是从“硬编码连接”、“SQL 拼接”、“手写 Swing 绝对布局”和“连接泄露”走过来的。

*   **如果现在的你回头看这段代码觉得“惨不忍睹”，这恰恰说明你在过去的三年里获得了长足的进步，积累了更深厚的技术功底。**
*   在如今的商业级后端开发中，我们已经不再手写这些原始的 JDBC 逻辑，而是采用 **Spring Boot + Spring Data JPA / MyBatis** 自动管理连接、使用 **数据库连接池 (HikariCP)** 避免泄露、用 **Web 前端 (Vue/React)** 代替 Swing 桌面客户端。

希望这份 Review 能带给你一些关于旧时光的亲切回忆，也能见证你技术路上的成长！
