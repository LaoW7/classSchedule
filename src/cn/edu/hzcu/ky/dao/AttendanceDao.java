package cn.edu.hzcu.ky.dao;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Time;

import cn.edu.hzcu.ky.model.BeanAttendance;
import cn.edu.hzcu.ky.util.DBUtil;

public class AttendanceDao {
    public static int addattendance(BeanAttendance attendance){
        int id = 0;
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "insert into attendance(StudentID, Date, SignInTime,SignOutTime,attendanceType) values (?, ?, ?, ?, ?)";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, attendance.getStudentID());
            pst.setDate(2, attendance.getDate());
            pst.setTime(3, attendance.getSignInTime());
            pst.setTime(4, attendance.getSignOutTime());
            pst.setString(5, attendance.getAttendanceType());
            pst.execute();
            id=1;
            pst.close();
        } catch (Exception e) {
            id=0;
            e.printStackTrace();
        }finally {
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

    //Find and delete this information according to StudentID, Date, SignInTime, SignOutTime, AttendanceType
    public static int deleteAttendance(String studentid,String date,String SignInTime,String SignOutTime,String AttendanceType) throws SQLException{
        int id = 0;
        Connection conn = DBUtil.getConnection();
        String sql="delete from attendance where StudentID=? and Date=? and SignInTime=? and SignOutTime=? and AttendanceType=?";
        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, studentid);
        pst.setString(2, date);
        pst.setString(3, SignInTime);
        pst.setString(4, SignOutTime);
        pst.setString(5, AttendanceType);
        pst.execute();
        id=1;
        pst.close();
       
        return id;
    }

    public static ResultSet loadAllAttendance(Connection conn)throws SQLException{
        String sql = "select StudentID,Date,SignInTime,SignOutTime,AttendanceType from attendance";
        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        return rs;
    }
    //AttendanceDao.searchSpecificAttendance(startYear, startMonth, startDay, startHour, startMinute, endYear, endMonth, endDay, endHour, endMinute, studentId, name, enrollmentYear, major);
    public static ResultSet searchSpecificAttendance(String startYear,String startMonth,String startDay,String startHour,String startMinute,String endYear,String endMonth,String endDay,String endHour,String endMinute,String studentId,String name,String enrollmentYear,String major,String attendanceType) throws SQLException{
        Connection conn = DBUtil.getConnection();
        String sql = "select StudentID,Date,SignInTime,SignOutTime,AttendanceType from attendance where 1=1";
        if(startYear!=null && !startYear.equals("")){
            sql+=" and Date>='"+startYear+"-"+startMonth+"-"+startDay+" "+startHour+":"+startMinute+":00'";
        }
        if(endYear!=null && !endYear.equals("")){
            sql+=" and Date<='"+endYear+"-"+endMonth+"-"+endDay+" "+endHour+":"+endMinute+":00'";
        }
        if(studentId!=null && !studentId.equals("")){
            sql+=" and StudentID='"+studentId+"'";
        }
        if(name!=null && !name.equals("")){
            sql+=" and StudentID in (select StudentID from student where Name='"+name+"')";
        }
        if(enrollmentYear!=null && !enrollmentYear.equals("")){
            sql+=" and StudentID in (select StudentID from student where EnrollmentYear='"+enrollmentYear+"')";
        }
        if(major!=null && !major.equals("")){
            sql+=" and StudentID in (select StudentID from student where Major='"+major+"')";
        }
        if(attendanceType!=null && !attendanceType.equals("")){
            sql+=" and AttendanceType='"+attendanceType+"'";
        }
        sql+=" order by Date desc";
        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        return rs;
        
    }
    //fillAttendanceTable according to attendanceType,group by StudentID and count by studentID,升序
    public static ResultSet searchSpecificAttendanceAsc(String attendanceType) throws SQLException{
        Connection conn = DBUtil.getConnection();
        String sql = "select StudentID,AttendanceType,count(StudentID) as count from attendance where AttendanceType=? group by StudentID order by count asc";
        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, attendanceType);
        ResultSet rs = pst.executeQuery();
        return rs;
        
    }
    //fillAttendanceTable according to attendanceType,group by StudentID and count by studentID,降序
    public static ResultSet searchSpecificAttendanceDesc(String attendanceType) throws SQLException{
        Connection conn = DBUtil.getConnection();
        String sql = "select StudentID,AttendanceType,count(StudentID) as count from attendance where AttendanceType=? group by StudentID order by count desc";
        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, attendanceType);
        ResultSet rs = pst.executeQuery();
        return rs;
        
    }

}
