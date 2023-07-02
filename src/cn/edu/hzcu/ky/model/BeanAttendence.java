package cn.edu.hzcu.ky.model;

import java.util.Date;

public class BeanAttendence {
    private String AttendenceID;
    private String StudentID;
    private Date Date;
    private Date SignInTime;
    private Date SignOutTime;
    private String AttendenceType;

    public String getAttendenceID() {
        return AttendenceID;
    }
    public void setAttendenceID(String attendenceID) {
        AttendenceID = attendenceID;
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
    public void setDate(java.util.Date date) {
        Date = date;
    }
    public Date getSignInTime() {
        return SignInTime;
    }
    public void setSignInTime(Date signInTime) {
        SignInTime = signInTime;
    }
    public Date getSignOutTime() {
        return SignOutTime;
    }
    public void setSignOutTime(Date signOutTime) {
        SignOutTime = signOutTime;
    }
    public String getAttendenceType() {
        return AttendenceType;
    }
    public void setAttendenceType(String attendenceType) {
        AttendenceType = attendenceType;
    }
}
