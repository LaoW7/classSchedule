package cn.edu.hzcu.ky.model;
/*
 * 学生表
 * `StudentID` varchar(255) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `LoginPassword` varchar(255) DEFAULT NULL,
  `Class` varchar(255) DEFAULT NULL,
  `EnrollmentYear` varchar(255) DEFAULT NULL,
  `Major` varchar(255) DEFAULT NULL,
  `PhoneNumber` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
 */
public class BeanStudent {
    private String StudentID;
    private String Name;
    private String LoginPassword;
    private String Class;
    private String EnrollmentYear;
    private String Major;
    private String PhoneNumber;
    private String Email;
    public String getStudentID() {
        return StudentID;
    }
    public void setStudentID(String studentID) {
        StudentID = studentID;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getLoginPassword() {
        return LoginPassword;
    }
    public void setLoginPassword(String loginPassword) {
        LoginPassword = loginPassword;
    }
    public String getClass1() {
        return Class;
    }
    public void setClass1(String class1) {
        Class = class1;
    }
    public String getEnrollmentYear() {
        return EnrollmentYear;
    }
    public void setEnrollmentYear(String enrollmentYear) {
        EnrollmentYear = enrollmentYear;
    }
    public String getMajor() {
        return Major;
    }
    public void setMajor(String major) {
        Major = major;
    }
    public String getPhoneNumber() {
        return PhoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
}
