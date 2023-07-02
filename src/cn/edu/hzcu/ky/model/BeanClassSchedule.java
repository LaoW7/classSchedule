package cn.edu.hzcu.ky.model;

public class BeanClassSchedule {
    private String ClassScheduleID;
    private String CourseID;
    private String ClassName;
    private String Semester;
    private int IsSpecial;

    public String getClassScheduleID() {
        return ClassScheduleID;
    }
    public void setClassScheduleID(String classScheduleID) {
        ClassScheduleID = classScheduleID;
    }
    public String getCourseID() {
        return CourseID;
    }
    public void setCourseID(String courseID) {
        CourseID = courseID;
    }
    public String getClassName() {
        return ClassName;
    }
    public void setClassName(String className) {
        ClassName = className;
    }
    public String getSemester() {
        return Semester;
    }
    public void setSemester(String semester) {
        Semester = semester;
    }
    public int getIsSpecial() {
        return IsSpecial;
    }
    public void setIsSpecial(int isSpecial) {
        IsSpecial = isSpecial;
    }
    
}
