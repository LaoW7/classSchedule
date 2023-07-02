package cn.edu.hzcu.ky.model;

import java.util.Date;


/*
 * 课程表
 * `TimeSlotID` varchar(255) NOT NULL,
  `TimeSlotName` varchar(255) DEFAULT NULL,
  `StartTime` time DEFAULT NULL,
  `EndTime` time DEFAULT NULL,
 */
public class BeanTimeSlot {
    private String TimeSlotID;
    private String TimeSlotName;
    private Date StartTime;
    private Date EndTime;
    public String getTimeSlotID() {
        return TimeSlotID;
    }
    public void setTimeSlotID(String timeSlotID) {
        TimeSlotID = timeSlotID;
    }
    public String getTimeSlotName() {
        return TimeSlotName;
    }
    public void setTimeSlotName(String timeSlotName) {
        TimeSlotName = timeSlotName;
    }
    public Date getStartTime() {
        return StartTime;
    }
    public void setStartTime(Date startTime) {
        StartTime = startTime;
    }
    public Date getEndTime() {
        return EndTime;
    }
    public void setEndTime(Date endTime) {
        EndTime = endTime;
    }
}
