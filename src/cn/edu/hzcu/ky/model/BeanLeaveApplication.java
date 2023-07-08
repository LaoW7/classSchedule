package cn.edu.hzcu.ky.model;

import java.sql.Date;

/*
 * 请假申请表
 * `LeaveID` varchar(255) NOT NULL,
  `StudentID` varchar(255) DEFAULT NULL,
  `StartDate` date DEFAULT NULL,
  `EndDate` date DEFAULT NULL,
  `TimeSlot` varchar(255) DEFAULT NULL,
  `Reason` varchar(255) DEFAULT NULL,
 */
public class BeanLeaveApplication {
    private int LeaveID;
    private String StudentID;
    private Date StartDate;
    private Date EndDate;
    private String TimeSlot;
    private String Reason;

    public BeanLeaveApplication() {
    }
    public BeanLeaveApplication(int leaveID, String studentID, Date startDate, Date endDate, String timeSlot,
            String reason) {
        LeaveID = leaveID;
        StudentID = studentID;
        StartDate = startDate;
        EndDate = endDate;
        TimeSlot = timeSlot;
        Reason = reason;
    }
    public BeanLeaveApplication(String studentID, Date startDate, Date endDate, String timeSlot,
            String reason) {
        StudentID = studentID;
        StartDate = startDate;
        EndDate = endDate;
        TimeSlot = timeSlot;
        Reason = reason;
    }


    public int getLeaveID() {
        return LeaveID;
    }
    public void setLeaveID(int leaveID) {
        LeaveID = leaveID;
    }
    public String getStudentID() {
        return StudentID;
    }
    public void setStudentID(String studentID) {
        StudentID = studentID;
    }
    public Date getStartDate() {
        return StartDate;
    }
    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }
    public Date getEndDate() {
        return EndDate;
    }
    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }
    public String getTimeSlot() {
        return TimeSlot;
    }
    public void setTimeSlot(String timeSlot) {
        TimeSlot = timeSlot;
    }
    public String getReason() {
        return Reason;
    }
    public void setReason(String reason) {
        Reason = reason;
    }
}
