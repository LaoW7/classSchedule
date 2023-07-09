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
import cn.edu.hzcu.ky.view.SelectClassFrm;


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
            String sql = "insert into detailedclassschedule(ClassScheduleID,TimeSlot,WeekID,WeekSlot) values(?,?,?,?)";

            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, bDetailClassSchedule.getClassScheduleID());
            pst.setString(2, bDetailClassSchedule.getTimeSlot());
            pst.setString(3, bDetailClassSchedule.getWeekID());
            pst.setString(4, bDetailClassSchedule.getWeekSlot());
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
                    "timeslot.TimeSlotID,\r\n" + //
                    "detailedclassschedule.WeekSlot\r\n" + //
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


        //添加选课信息,根据studentID，courseID返回1成功，0失败

        // public  static  int addCourseRegistration(String studentID,String courseID,String semester,String timeSlot) {
        //     int id = 0;
        //     Connection conn = null;
        //     try {
        //         conn = DBUtil.getConnection();

        //         //在此处添加互斥判断

        //         String sql = "insert into courseregistration(StudentID,CourseID,Semester,TimeSlot) values(?,?,?,?)";
        //         java.sql.PreparedStatement pst = conn.prepareStatement(sql);
        //         pst.setString(1, studentID);
        //         pst.setString(2, courseID);
        //         pst.setString(3, semester);
        //         pst.setString(4, timeSlot);
        //         pst.execute();
        //         pst.close();
        //         id=1;
        //     } catch (SQLException e) {
        //         e.printStackTrace();
        //         id=0;
        //         try {
        //             throw new DbException(e);
        //         } catch (DbException e1) {
        //             e1.printStackTrace();
        //         }
        //     } finally {
        //         if (conn != null) {
        //             try {
        //                 conn.close();
        //             } catch (SQLException e) {
        //                 e.printStackTrace();
        //             }
        //         }
        //     }
        //     return id;
        // }
        public static int deleteCourseSchedule(String CourseID, String ClassName, String Semester) throws BaseException{
            Connection conn = null;
            try {
                conn = DBUtil.getConnection();
                String sql = "select ClassScheduleID from classschedule where CourseID = ? and ClassName = ? and Semester = ?";
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, CourseID);
                pst.setString(2, ClassName);
                pst.setString(3, Semester);
                java.sql.ResultSet rs = pst.executeQuery();
                if(!rs.next()){
                    throw new BaseException("课程时间表不存在");
                }
                int classScheduleID = rs.getInt("ClassScheduleID");
                rs.close();
                pst.close();

                // Check if the class is selected in the courseregistration table
                System.out.println(classScheduleID);
                sql = "select count(*) as count\r\n" + //
                        "from courseregistration\r\n" + //
                        "WHERE\r\n" + //
                        "courseregistration.TimeSlot IN ((select TimeSlot from detailedclassschedule where ClassScheduleID = 37)) AND\r\n" + //
                        "courseregistration.CourseID = ?\r\n" + //
                        "";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, classScheduleID);
                pst.setString(1, CourseID);
                rs = pst.executeQuery();
                rs.next();
                System.out.println(rs.getInt("count"));
                if(rs.getInt("count") > 0){
                    return 0;
                }
                rs.close();
                pst.close();

                // delete from detailedclassschedule
                sql = "delete from detailedclassschedule where ClassScheduleID = ?";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, classScheduleID);
                pst.execute();
                pst.close();

                // delete from classschedule
                sql = "delete from classschedule where ClassScheduleID = ?";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, classScheduleID);
                pst.execute();
                pst.close();
                return 1;
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
        public static int addCourseRegistration(String studentID, String courseID, String semester, String timeSlotID) {
            int id = 0;
            Connection conn = null;
            try {
                conn = DBUtil.getConnection();
                //System.out.println(SelectClassFrm.isSpecial);
                if(SelectClassFrm.isSpecial=="否"){
                    // Check if the student has already registered for a course in the same time slot and same semester.
                    String checkSql = "select count(*) as count,Semester,CourseID from courseregistration where StudentID = ? and TimeSlot  = ?";
                    PreparedStatement checkPst = conn.prepareStatement(checkSql);
                    checkPst.setString(1, studentID);
                    checkPst.setString(2, timeSlotID);
                    ResultSet rs = checkPst.executeQuery();

                    if (rs.next() && rs.getInt(1) > 0 && rs.getString("Semester").equals(semester) && rs.getString("CourseID").equals(courseID) ) {


                        id=-3;
                        return id;
                    }else if(rs.getInt(1) > 0 && rs.getString("Semester").equals(semester)){
                        id=-1;
                        return id;
                    }
                    rs.close();
                    checkPst.close();
                }

                

                String sql = "insert into courseregistration(StudentID, CourseID, Semester, TimeSlot) values(?,?,?,?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, studentID);
                pst.setString(2, courseID);
                pst.setString(3, semester);
                pst.setString(4, timeSlotID);
                pst.execute();
                pst.close();
                id = 1;
            } catch (SQLException e) {
                e.printStackTrace();
                id = 0;
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


        public static int deleteCourseRegistration(String StudentID,String CourseName,String semester,String timeslot){
            int i = 0;
            //根据CourseName在classschedule中找到CourseID,根据timeslot(name)在timeslot表中找到TimeSlotID
            Connection conn = null;
            try {
                conn = DBUtil.getConnection();
                String sql = "select CourseID from classschedule where ClassName = ?";
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                //System.out.println(CourseName);
                pst.setString(1, CourseName);
                java.sql.ResultSet rs = pst.executeQuery();
                if(!rs.next()){
                    throw new BaseException("课程不存在");
                }
                String courseID = rs.getString("CourseID");
                rs.close();
                pst.close();

                sql = "select TimeSlotID from timeslot where TimeSlotName = ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, timeslot);
                rs = pst.executeQuery();
                if(!rs.next()){
                    throw new BaseException("时间段不存在");
                }
                String timeSlotID = rs.getString("TimeSlotID");
                rs.close();
                pst.close();

                // delete from courseregistration
                sql = "delete from courseregistration where StudentID = ? and CourseID = ? and Semester = ? and TimeSlot = ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, StudentID);
                pst.setString(2, courseID);
                pst.setString(3, semester);
                pst.setString(4, timeSlotID);
                pst.execute();
                pst.close();
                i = 1;
            } catch (SQLException e) {
                e.printStackTrace();
                i = 0;
                try {
                    throw new DbException(e);
                } catch (DbException e1) {
                    e1.printStackTrace();
                }
            } catch (BaseException e) {
                e.printStackTrace();
            } finally{
                if(conn!=null){
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            return i;
        }
}
