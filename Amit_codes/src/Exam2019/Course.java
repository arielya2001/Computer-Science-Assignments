package Exam2019;

public class Course {

    private int courseNum ,point,finalGrade;
    private String courseName;

    public Course(int courseNum ,String courseName,int point,int finalGrade)
    {
        this.courseNum=courseNum;
        this.courseName=courseName;
        this.point=point;
        this.finalGrade=finalGrade;
    }
    public Course(int courseNum ,String courseName,int point)
    {
        this.courseNum=courseNum;
        this.courseName=courseName;
        this.point=point;
        this.finalGrade=finalGrade;
    }
    public Course(Course c)
    {
        this(c.courseNum,c.courseName,c.point,c.finalGrade);
    }
    public int getCourseNum()
    {
        return courseNum;
    }

    public int getFinalGrade() {
        return finalGrade;
    }

    public String getCourseName()
    {
        return courseName;
    }
    public int getPoint()
    {
        return point;
    }
    public void setFinalGrade(int finalGrade)
    {
        this.finalGrade=finalGrade;
    }

}
