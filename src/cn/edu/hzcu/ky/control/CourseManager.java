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
        course.setCourseID("C0001");
        course.setCourseName("Java程序设计");
        course.setCredits(4);
        try {
            new CourseManager().addCourse(course);
            System.out.println("添加成功");
        } catch (BaseException e) {
            e.printStackTrace();
        }
    }
}
