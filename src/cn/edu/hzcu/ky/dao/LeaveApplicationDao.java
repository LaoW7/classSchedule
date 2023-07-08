package cn.edu.hzcu.ky.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//import javax.naming.spi.DirStateFactory.Result;

import cn.edu.hzcu.ky.model.BeanLeaveApplication;
import cn.edu.hzcu.ky.util.DBUtil;

public class LeaveApplicationDao {

    //根据studentID查询请假信息
        public static ResultSet getLeaveApplicationByStudentID(String studentID,Connection conn) {
        String sql = "select * from leaveapplication where StudentID = ?";
        ResultSet rs = null;  // 添加一个变量存储ResultSet对象
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, studentID);
            rs = pst.executeQuery();  // 将结果存储在ResultSet对象中
            // while (rs.next()) {
            //     System.out.println(rs.getString(1));
            // }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return rs;  
    }
    public static int deleteLeaveApplication(BeanLeaveApplication leaveApplication){
        int result = 0;
        String sql = "delete from leaveapplication where StudentID = ? and StartDate = ? and EndDate = ? and TimeSlot = ? and Reason = ?";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, leaveApplication.getStudentID());
            pst.setDate(2, leaveApplication.getStartDate());
            pst.setDate(3, leaveApplication.getEndDate());
            pst.setString(4, leaveApplication.getTimeSlot());
            pst.setString(5, leaveApplication.getReason());
            pst.execute();
            result = 1;
            pst.close();
        } catch (Exception e) {
            result = 0;
            e.printStackTrace();
        }
        return result;
    }




    //在数据库中添加请假信息
    public static int addLeaveApplication(BeanLeaveApplication leaveApplication){
        int result = 0;
        String sql = "insert into leaveapplication(StudentID,StartDate,EndDate,TimeSlot,Reason) values(?,?,?,?,?)";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, leaveApplication.getStudentID());
            pst.setDate(2, leaveApplication.getStartDate());
            pst.setDate(3, leaveApplication.getEndDate());
            pst.setString(4, leaveApplication.getTimeSlot());
            pst.setString(5, leaveApplication.getReason());
            pst.execute();
            result = 1;
            pst.close();
        } catch (Exception e) {
            result = 0;
            e.printStackTrace();
        }
        return result;
    }
}
