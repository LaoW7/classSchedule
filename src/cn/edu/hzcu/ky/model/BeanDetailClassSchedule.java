package cn.edu.hzcu.ky.model;
/*
 * 课程表
 * `ClassScheduleID` varchar(255) DEFAULT NULL,
  `SerialNumber` varchar(255) DEFAULT NULL,
  `TimeSlot` varchar(255) DEFAULT NULL,
  `WeekID` varchar(255) DEFAULT NULL,
 * 
 */
public class BeanDetailClassSchedule {
    private int ClassScheduleID;
    private String SerialNumber;
    private String TimeSlot;
    private String WeekID;

    public BeanDetailClassSchedule() {
    }
    public BeanDetailClassSchedule(int classScheduleID, String timeSlot,
            String weekID) {
        ClassScheduleID = classScheduleID;
        TimeSlot = timeSlot;
        WeekID = weekID;
    }

    public int getClassScheduleID() {
        return ClassScheduleID;
    }
    public void setClassScheduleID(int classScheduleID) {
        ClassScheduleID = classScheduleID;
    }
    public String getSerialNumber() {
        return SerialNumber;
    }
    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }
    public String getTimeSlot() {
        return TimeSlot;
    }
    public void setTimeSlot(String timeSlot) {
        TimeSlot = timeSlot;
    }
    public String getWeekID() {
        return WeekID;
    }
    public void setWeekID(String weekID) {
        WeekID = weekID;
    }
}
