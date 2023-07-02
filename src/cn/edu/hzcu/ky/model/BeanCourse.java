package cn.edu.hzcu.ky.model;
/*
 * 课程表
 * `CourseID` varchar(255) NOT NULL,
  `CourseName` varchar(255) DEFAULT NULL,
  `Credits` double(20,0) DEFAULT NULL,
 */
public class BeanCourse {
    private String CourseID;
    private String CourseName;
    private double Credits;
    public String getCourseID() {
        return CourseID;
    }
    public void setCourseID(String courseID) {
        CourseID = courseID;
    }
    public String getCourseName() {
        return CourseName;
    }
    public void setCourseName(String courseName) {
        CourseName = courseName;
    }
    public double getCredits() {
        return Credits;
    }
    public void setCredits(double credits) {
        Credits = credits;
    }
}
