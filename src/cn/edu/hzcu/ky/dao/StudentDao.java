package cn.edu.hzcu.ky.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import javax.naming.spi.DirStateFactory.Result;

import cn.edu.hzcu.ky.model.BeanStudent;
import cn.edu.hzcu.ky.util.DBUtil;
import cn.edu.hzcu.ky.util.StringUtil;


public class StudentDao {//学生登录
    
    public BeanStudent login(Connection conn, BeanStudent student) throws Exception {
        BeanStudent result = null;
        String sql = "select * from student where StudentID=? and LoginPassword=?";
        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, student.getStudentID());
        pst.setString(2, student.getLoginPassword());
        java.sql.ResultSet rs = pst.executeQuery();
        if(rs.next()){
            result = new BeanStudent();
            result.setStudentID(rs.getString(1));
            result.setName(rs.getString(2));
            result.setLoginPassword(rs.getString(3));
            result.setClass1(rs.getString(4));
            result.setEnrollmentYear(rs.getString(5));
            result.setMajor(rs.getString(6));
            result.setPhoneNumber(rs.getString(7));
            result.setEmail(rs.getString(8));
        }

        return result;
    }


    public static ResultSet list(Connection conn, BeanStudent student) throws Exception {
        StringBuffer sb = new StringBuffer("select * from student");
        if(student!=null && !StringUtil.isEmpty(student.getStudentID())){
            sb.append(" and StudentID like '%"+student.getStudentID()+"%'");
        }
        if(student!=null && !StringUtil.isEmpty(student.getName())){
            sb.append(" and name like '%"+student.getName()+"%'");
        }
        if(student!=null && !StringUtil.isEmpty(student.getLoginPassword())){
            sb.append(" and LoginPassword like '%"+student.getLoginPassword()+"%'");
        }
        if(student!=null && !StringUtil.isEmpty(student.getClass1())){
            sb.append(" and class like '%"+student.getClass1()+"%'");
        }
        if(student!=null && !StringUtil.isEmpty(student.getEnrollmentYear())){
            sb.append(" and EnrollmentYear like '%"+student.getEnrollmentYear()+"%'");
        }
        if(student!=null && !StringUtil.isEmpty(student.getMajor())){
            sb.append(" and major like '%"+student.getMajor()+"%'");
        }
        if(student!=null && !StringUtil.isEmpty(student.getPhoneNumber())){
            sb.append(" and PhoneNumber like '%"+student.getPhoneNumber()+"%'");
        }
        if(student!=null && !StringUtil.isEmpty(student.getEmail())){
            sb.append(" and Email like '%"+student.getEmail()+"%'");
        }
        PreparedStatement pst = conn.prepareStatement(sb.toString().replaceFirst("and", "where"));
        ResultSet rs = pst.executeQuery();
        return rs;
    }

    public static int update(Connection conn, BeanStudent student) throws Exception {
		String sql = "update student set Name=?,LoginPassword=?,Class=?,EnrollmentYear=?,Major=?,PhoneNumber=?,Email=? where StudentID=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, student.getName());
		pstmt.setString(2, student.getLoginPassword());
		pstmt.setString(3, student.getClass1());
		pstmt.setString(4, student.getEnrollmentYear());
		pstmt.setString(5, student.getMajor());
		pstmt.setString(6, student.getPhoneNumber());
		pstmt.setString(7, student.getEmail());
		pstmt.setString(8, student.getStudentID());
		return pstmt.executeUpdate();
	}
    public static int add(Connection conn, BeanStudent student) throws Exception {
        String sql = "insert into student values(?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, student.getStudentID());
        pstmt.setString(2, student.getName());
        pstmt.setString(3, student.getLoginPassword());
        pstmt.setString(4, student.getClass1());
        pstmt.setString(5, student.getEnrollmentYear());
        pstmt.setString(6, student.getMajor());
        pstmt.setString(7, student.getPhoneNumber());
        pstmt.setString(8, student.getEmail());
        return pstmt.executeUpdate();
    }

    public static ResultSet loadAllStudent(Connection conn) throws Exception {
        String sql = "select * from student";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        return rs;
    }

    //根据StudentID，查询Name、EnrollmentYear、Major
    public static BeanStudent searchSpecStudent(String studentID){
        BeanStudent result = null;
        try {
            Connection conn = DBUtil.getConnection();
            String sql = "select Name,EnrollmentYear,Major from student where StudentID=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, studentID);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                result = new BeanStudent();
                result.setName(rs.getString(1));
                result.setEnrollmentYear(rs.getString(2));
                result.setMajor(rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }


    
//    public void main(String[] args) throws Exception {//The test function test prints the result returned by the login() method
//        Connection conn = DBUtil.getConnection();
//        BeanStudent stu = new BeanStudent("32101118","123456");
//        System.out.println(login(conn, stu).getName());
//        System.out.println(login(conn, stu).getLoginPassword());
//    }
    
}
