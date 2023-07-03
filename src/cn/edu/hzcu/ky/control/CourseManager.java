package cn.edu.hzcu.ky.control;
import java.sql.Connection;
import java.sql.SQLException;

/*
 * Implement the operation of adding, deleting, modifying, and querying the course database.
 */
import cn.edu.hzcu.ky.model.*;
import cn.edu.hzcu.ky.util.BaseException;
import cn.edu.hzcu.ky.util.DBUtil;
import cn.edu.hzcu.ky.util.DbException;


public class CourseManager {
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
        public static void main(String[] args){
        BeanCourse course = new BeanCourse();
        //查询C0001课程
        try {
            course = new CourseManager().searchCourse("C0001");
            System.out.println(course.getCourseID());
            System.out.println(course.getCourseName());
            System.out.println(course.getCredits());
        } catch (BaseException e) {
            e.printStackTrace();
        }
    }
}
