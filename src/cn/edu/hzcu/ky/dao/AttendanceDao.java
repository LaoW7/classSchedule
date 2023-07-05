package cn.edu.hzcu.ky.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

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

}
