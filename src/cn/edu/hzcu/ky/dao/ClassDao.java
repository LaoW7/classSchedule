package cn.edu.hzcu.ky.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.hzcu.ky.model.BeanClassSchedule;
import cn.edu.hzcu.ky.model.BeanDetailClassSchedule;
import cn.edu.hzcu.ky.util.BaseException;
import cn.edu.hzcu.ky.util.DBUtil;
import cn.edu.hzcu.ky.util.DbException;
import cn.edu.hzcu.ky.view.LoginOnFrm;

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
     public static  ResultSet loadYourAllClass(Connection conn) throws SQLException{
        String sql="SELECT\r\n" + //
                "classschedule.ClassName,\r\n" + //
                "classschedule.IsSpecial,\r\n" + //
                "classschedule.Semester,\r\n" + //
                "timeslot.TimeSlotName,\r\n" + //
                "`week`.WeekName\r\n" + //
                "FROM\r\n" + //
                "courseregistration\r\n" + //
                "INNER JOIN detailedclassschedule ON courseregistration.TimeSlot = detailedclassschedule.TimeSlot\r\n" + //
                "INNER JOIN timeslot ON detailedclassschedule.TimeSlot = timeslot.TimeSlotID\r\n" + //
                "INNER JOIN classschedule ON courseregistration.Semester = classschedule.Semester AND detailedclassschedule.ClassScheduleID = classschedule.ClassScheduleID\r\n" + //
                "INNER JOIN `week` ON detailedclassschedule.WeekID = `week`.WeekID\r\n" + //
                "WHERE\r\n" + //
                "    courseregistration.StudentID = ? \r\n" + //
                "    AND courseregistration.CourseID = classschedule.CourseID\r\n" + //
                "";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1,LoginOnFrm.userid);
        //System.out.println(LoginOnFrm.userid);
        ResultSet rs = pst.executeQuery();
        return rs;
     }


     public static  ResultSet loadAllClass(Connection conn) throws SQLException {
            String sql = "SELECT\r\n" + //
                    "classschedule.CourseID AS CourseID,\r\n" + //
                    "classschedule.ClassName AS ClassName,\r\n" + //
                    "classschedule.Semester AS Semester,\r\n" + //
                    "classschedule.IsSpecial AS IsSpecial,\r\n" + //
                    "timeslot.TimeSlotName AS TimeSlotName,\r\n" + //
                    "`week`.WeekName AS WeekName,\r\n" + //
                    "timeslot.TimeSlotID\r\n" + //
                    "FROM\r\n" + //
                    "\t(\r\n" + //
                    "\t\t(\r\n" + //
                    "\t\t\t(\r\n" + //
                    "\t\t\t\t`classschedule`\r\n" + //
                    "\t\t\t\tJOIN `detailedclassschedule` ON (\r\n" + //
                    "\t\t\t\t\t(\r\n" + //
                    "\t\t\t\t\t\t`detailedclassschedule`.`ClassScheduleID` = `classschedule`.`ClassScheduleID`\r\n" + //
                    "\t\t\t\t\t)\r\n" + //
                    "\t\t\t\t)\r\n" + //
                    "\t\t\t)\r\n" + //
                    "\t\t\tJOIN `timeslot` ON (\r\n" + //
                    "\t\t\t\t(\r\n" + //
                    "\t\t\t\t\t`detailedclassschedule`.`TimeSlot` = `timeslot`.`TimeSlotID`\r\n" + //
                    "\t\t\t\t)\r\n" + //
                    "\t\t\t)\r\n" + //
                    "\t\t)\r\n" + //
                    "\t\tJOIN `week` ON (\r\n" + //
                    "\t\t\t(\r\n" + //
                    "\t\t\t\t`detailedclassschedule`.`WeekID` = `week`.`WeekID`\r\n" + //
                    "\t\t\t)\r\n" + //
                    "\t\t)\r\n" + //
                    "\t)\r\n" + //
                    "";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            return rs;
        }


        //添加选课信息,返回1成功，0失败

        public  static  int addCourseRegistration(String studentID,String courseID,String semester,String timeSlot) {
            int id = 0;
            Connection conn = null;
            try {
                conn = DBUtil.getConnection();
                String sql = "insert into courseregistration(StudentID,CourseID,Semester,TimeSlot) values(?,?,?,?)";
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, studentID);
                pst.setString(2, courseID);
                pst.setString(3, semester);
                pst.setString(4, timeSlot);
                pst.execute();
                pst.close();
                id=1;
            } catch (SQLException e) {
                e.printStackTrace();
                id=0;
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
        
}
