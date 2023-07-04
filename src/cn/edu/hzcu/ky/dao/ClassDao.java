package cn.edu.hzcu.ky.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.hzcu.ky.model.BeanClassSchedule;
import cn.edu.hzcu.ky.model.BeanDetailClassSchedule;
import cn.edu.hzcu.ky.util.DBUtil;
import cn.edu.hzcu.ky.util.DbException;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class ClassDao {
    public static int addClassSchedule(BeanClassSchedule classSchedule) {
        int id = 0;
    Connection conn = null;
    try {
        conn = DBUtil.getConnection();
        String sql = "insert into classschedule(CourseID, ClassName, Semester, IsSpecial) values (?, ?, ?, ?)";
        java.sql.PreparedStatement pst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setString(1, classSchedule.getCourseID());
        pst.setString(2, classSchedule.getClassName());
        pst.setString(3, classSchedule.getSemester());
        pst.setInt(4, classSchedule.getIsSpecial());
        pst.execute();

        ResultSet generatedKeys = pst.getGeneratedKeys();
        if (generatedKeys.next()) {
            id = generatedKeys.getInt(1);
            classSchedule.setClassScheduleID(id); // 设置生成的ID值到对象中
            System.out.println("Generated ID: " + id);
        }

        pst.close();
    } catch (SQLException e) {
        e.printStackTrace();
        try {
            throw new DbException(e);
        } catch (DbException e1) {
            e1.printStackTrace();
        }
    } finally {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    return id;
}

     public static void addDetailClassSchedule(BeanDetailClassSchedule bDetailClassSchedule){
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "insert into detailedclassschedule(ClassScheduleID,TimeSlot,WeekID) values(?,?,?)";

            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, bDetailClassSchedule.getClassScheduleID());
            pst.setString(2, bDetailClassSchedule.getTimeSlot());
            pst.setString(3, bDetailClassSchedule.getWeekID());
            pst.execute();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                throw new DbException(e);
            } catch (DbException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
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
}
