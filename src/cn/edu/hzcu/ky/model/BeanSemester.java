package cn.edu.hzcu.ky.model;

import java.util.Date;

/*
 * 学期表
 * `SemesterID` varchar(255) NOT NULL,
  `AcademicYear` varchar(255) DEFAULT NULL,
  `Semester` varchar(255) DEFAULT NULL,
  `StartDate` date DEFAULT NULL,
  `EndDate` date DEFAULT NULL,
 */
public class BeanSemester {
    private String SemesterID;
    private String AcademicYear;
    private String Semester;
    private Date StartDate;
    private Date EndDate;

    
    public String getSemesterID() {
        return SemesterID;
    }
    public void setSemesterID(String semesterID) {
        SemesterID = semesterID;
    }
    public String getAcademicYear() {
        return AcademicYear;
    }
    public void setAcademicYear(String academicYear) {
        AcademicYear = academicYear;
    }
    public String getSemester() {
        return Semester;
    }
    public void setSemester(String semester) {
        Semester = semester;
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
}
