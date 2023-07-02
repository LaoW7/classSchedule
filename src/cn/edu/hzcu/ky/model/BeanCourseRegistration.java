package cn.edu.hzcu.ky.model;
/*
 * 课程注册表
 * `RegistrationID` varchar(255) NOT NULL,
  `StudentID` varchar(255) DEFAULT NULL,
  `CourseID` varchar(255) DEFAULT NULL,
  `Semester` varchar(255) DEFAULT NULL,
  `FullWeek` tinyint(1) DEFAULT NULL,
  `TimeSlot` varchar(255) DEFAULT NULL,
 */
public class BeanCourseRegistration {
    private String RegistrationID;
    private String StudentID;
    private String CourseID;
    private String Semester;
    private int FullWeek;
    private String TimeSlot;

    public String getRegistrationID() {
        return RegistrationID;
    }
    public void setRegistrationID(String registrationID) {
        RegistrationID = registrationID;
    }
    public String getStudentID() {
        return StudentID;
    }
    public void setStudentID(String studentID) {
        StudentID = studentID;
    }
    public String getCourseID() {
        return CourseID;
    }
    public void setCourseID(String courseID) {
        CourseID = courseID;
    }
    public String getSemester() {
        return Semester;
    }
    public void setSemester(String semester) {
        Semester = semester;
    }
    public int getFullWeek() {
        return FullWeek;
    }
    public void setFullWeek(int fullWeek) {
        FullWeek = fullWeek;
    }
    public String getTimeSlot() {
        return TimeSlot;
    }
    public void setTimeSlot(String timeSlot) {
        TimeSlot = timeSlot;
    }
    

}
