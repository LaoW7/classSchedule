# 选课管理系统

## 📋 项目简介

选课管理系统是一个基于 Java 的数据库应用系统，为教育机构提供完整的课程选课、管理和调度解决方案。系统支持学生选课、教师课程管理、系统管理员配置等功能，提供高效的课程安排和学生信息管理。

## ✨ 主要功能

- **学生选课**：学生可以浏览课程信息，进行课程选择和退选
- **课程管理**：教师和管理员可以创建、编辑、删除课程信息
- **班级管理**：管理班级信息、班级人数和班级课表
- **教室管理**：管理教室资源、容量和使用情况
- **成绩管理**：记录和查询学生成绩
- **选课统计**：生成选课数据报告和统计分析
- **用户权限管理**：区分学生、教师、管理员三种用户角色

## 🛠️ 技术栈

- **后端语言**：Java
- **数据库**：关系型数据库（如 MySQL/PostgreSQL）
- **框架**：可能使用 Spring、Spring Boot 等企业级框架
- **其他技术**：JDBC/ORM、Servlet 等

## 📁 项目结构

```
classSchedule/
├── src/                          # 源代码目录
│   ├── main/
│   │   ├── java/                 # Java 源文件
│   │   └── resources/            # 配置文件
│   └── test/                     # 测试代码
├── database/                     # 数据库相关文件
│   └── schema.sql               # 数据库建表脚本
├── doc/                         # 项目文档
├── README.md                    # 本文件
└── pom.xml                      # Maven 配置文件（如使用 Maven）
```

## 🚀 快速开始

### 环境要求

- Java 8 或更高版本
- MySQL 5.7+ 或 PostgreSQL 10+
- Maven 3.6+ （如使用 Maven）

### 安装步骤

1. **克隆仓库**
   ```bash
   git clone https://github.com/LaoW7/classSchedule.git
   cd classSchedule
   ```

2. **数据库配置**
   - 导入 `database/schema.sql` 文件创建数据库和表
   - 更新数据库连接配置文件中的用户名、密码和数据库地址

3. **编译和运行**
   ```bash
   # 使用 Maven 编译
   mvn clean compile
   
   # 打包项目
   mvn package
   
   # 运行应用
   java -jar target/classSchedule.jar
   ```

4. **访问系统**
   - 默认访问地址：`http://localhost:8080`
   - 使用初始化的用户凭证登录

## 🔐 用户角色说明

| 角色 | 权限 | 功能 |
|------|------|------|
| 学生 | 普通 | 查看课程、选课、退选、查看成绩 |
| 教师 | 中级 | 创建课程、查看选课学生、录入成绩 |
| 管理员 | 高级 | 全局管理、用户管理、系统配置 |

## 📊 数据库设计

系统主要包含以下核心表：

- **users** - 用户表
- **students** - 学生表
- **teachers** - 教师表
- **courses** - 课程表
- **classes** - 班级表
- **classrooms** - 教室表
- **enrollments** - 选课表
- **grades** - 成绩表

详见数据库设计文档或 SQL 脚本。

## 🤝 贡献

欢迎提交 Issue 和 Pull Request 来改进项目！

### 贡献步骤

1. Fork 本仓库
2. 创建你的特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 提交 Pull Request

## 📝 许可证

本项目采用 MIT 许可证 - 详见 [LICENSE](LICENSE) 文件

## 📧 联系方式

- **作者**：LaoW7
- **项目链接**：[https://github.com/LaoW7/classSchedule](https://github.com/LaoW7/classSchedule)

---

**最后更新**：2026年6月16日
