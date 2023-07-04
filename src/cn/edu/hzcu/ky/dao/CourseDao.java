package cn.edu.hzcu.ky.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;

/*
 * Implement the operation of adding, deleting, modifying, and querying the course database.
 */
import cn.edu.hzcu.ky.model.*;
import cn.edu.hzcu.ky.util.BaseException;
import cn.edu.hzcu.ky.util.DBUtil;
import cn.edu.hzcu.ky.util.DbException;


public class CourseDao {
    public BeanCourse searchCourse(String CourseID) throws BaseException{
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from course where CourseID = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, CourseID);
            java.sql.ResultSet rs = pst.executeQuery();
            if(!rs.next()){
                throw new BaseException("课程不存在");
            }
            BeanCourse course = new BeanCourse();
            course.setCourseID(rs.getString(1));
            course.setCourseName(rs.getString(2));
            course.setCredits(rs.getDouble(3));
            rs.close();
            pst.close();
            return course;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException(e);
        } finally{
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void addCourse(BeanCourse course) throws BaseException{
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "insert into course(CourseID,CourseName,Credits) values(?,?,?)";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, course.getCourseID());
            pst.setString(2, course.getCourseName());
            pst.setDouble(3, course.getCredits());
            pst.execute();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException(e);
        } finally{
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static ResultSet loadAllCourse(Connection conn) throws BaseException {
        ResultSet rs = null;
        try {
            String sql = "select * from course";
            PreparedStatement pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException(e);
        }
    }


        public static void main(String[] args) throws SQLException {
            BeanCourse course = new BeanCourse();
            Connection conn = null;
            ResultSet rSet = null;
            try {
                conn = DBUtil.getConnection();
                rSet = loadAllCourse(conn);
                if (rSet.next()) {
                    System.out.println(rSet.getString(1));
                    System.out.println(rSet.getString(2));
                    System.out.println(rSet.getDouble(3));
                }
            } catch (BaseException e) {
                e.printStackTrace();
            } finally {
                // 关闭结果集
                if (rSet != null) {
                    try {
                        rSet.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                // 关闭连接
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
}
