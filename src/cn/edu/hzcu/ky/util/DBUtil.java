package cn.edu.hzcu.ky.util;

import java.sql.Connection;
/**
 * 
 * @author 王凯一
 *
 */

public class DBUtil {
	private static final String dbUrl="jdbc:mysql://localhost:3306/class1";
    private static final String dbUserName="root";
    private static final String dbPassword="123456";
    private static final String jdbcName="com.mysql.cj.jdbc.Driver";
        public String getDbUrl() {
                return dbUrl;
        }
        public String getDbUserName() {
                return dbUserName;
        }
        public String getDbPassword() {
                return dbPassword;
        }
        public String getJdbcName() {
                return jdbcName;
        }
        public static Connection getConnection(){
                try {
                        Class.forName(jdbcName);
                        Connection con=java.sql.DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
                        return con;
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return null;
        }
        public static void closeConnection(Connection con){
                if(con!=null)
                        try {
                                con.close();
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
        }
        public static void main(String[] args) {
                try {
                        DBUtil.getConnection();
                        System.out.println("数据库连接成功");
                    
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                    System.out.println("数据库连接失败");
                }
                DBUtil.getConnection();
        }
}
