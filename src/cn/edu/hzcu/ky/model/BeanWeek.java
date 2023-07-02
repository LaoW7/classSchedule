package cn.edu.hzcu.ky.model;
/*
 * 课程表
 *  `WeekID` varchar(255) NOT NULL,
  `WeekNumber` varchar(255) DEFAULT NULL,
  `WeekName` varchar(255) DEFAULT NULL,
 */
public class BeanWeek {
    private String WeekID;
    private String WeekNumber;
    private String WeekName;
    public String getWeekID() {
        return WeekID;
    }
    public void setWeekID(String weekID) {
        WeekID = weekID;
    }
    public String getWeekNumber() {
        return WeekNumber;
    }
    public void setWeekNumber(String weekNumber) {
        WeekNumber = weekNumber;
    }
    public String getWeekName() {
        return WeekName;
    }
    public void setWeekName(String weekName) {
        WeekName = weekName;
    }
}
