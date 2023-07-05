package cn.edu.hzcu.ky.model;

import java.sql.Time;
import java.sql.Date;

public class BeanAttendance {
    private int AttendanceID;
    private String StudentID;
    private Date Date;
    private Time SignInTime;
    private Time SignOutTime;
    private String AttendanceType;

    public BeanAttendance() {
    }
    public BeanAttendance(int attendanceID, String studentID, Date date, Time signInTime, Time signOutTime,
            String attendanceType) {
        AttendanceID = attendanceID;
        StudentID = studentID;
        Date = date;
        SignInTime = signInTime;
        SignOutTime = signOutTime;
        AttendanceType = attendanceType;
    }
    //无ID构造方法
    public BeanAttendance(String studentID, Date date, Time signInTime, Time signOutTime, String attendanceType) {
        StudentID = studentID;
        Date = date;
        SignInTime = signInTime;
        SignOutTime = signOutTime;
        AttendanceType = attendanceType;
    }

    public int getAttendanceID() {
        return AttendanceID;
    }
    public void setAttendanceID(int attendanceID) {
        AttendanceID = attendanceID;
    }
    public String getStudentID() {
        return StudentID;
    }
    public void setStudentID(String studentID) {
        StudentID = studentID;
    }
    public Date getDate() {
        return Date;
    }
    public void setDate(Date date) {
        Date = date;
    }
    public Time getSignInTime() {
        return SignInTime;
    }
    public void setSignInTime(Time signInTime) {
        SignInTime = signInTime;
    }
    public Time getSignOutTime() {
        return SignOutTime;
    }
    public void setSignOutTime(Time signOutTime) {
        SignOutTime = signOutTime;
    }
    public String getAttendanceType() {
        return AttendanceType;
    }
    public void setAttendanceType(String attendanceType) {
        AttendanceType = attendanceType;
    }
}
