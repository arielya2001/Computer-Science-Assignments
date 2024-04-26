package Exam2019;

public class Student {

    private String name;
    private long id;
    private int numOfCourses;
    private Course [] courses;

    public Student(String name,long id)
    {this.name=name;
    this.id=id;
    courses=new Course[10];
    }

    public boolean addCourse(Course c)
    {
        if (numOfCourses>=10)
            return false;
        courses[numOfCourses]=new Course(c);
        numOfCourses++;
        return true;
    }
    public boolean setGrade(int grade , int numOfCourses)
    {
        if (grade<0||grade>100)
            return false;

        for (int i=0;i<numOfCourses;i++)
        {
            if (courses[i].getCourseNum()==numOfCourses) {
                courses[i].setFinalGrade(grade);
                return true;
            }
        }
        return false;
    }





}

