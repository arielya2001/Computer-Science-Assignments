package Ex6;

public class Class {
    private Student _student;
    public Class(Student student)
    {
        this._student =student;

    }
    public void changeGrade(int grade)
    {
        _student.setMolecularBiologyGrade(grade);

    }




    public static void main(String[]args)
    {
        System.out.println(Student.getNumOfStudents());
        Student student1=new Student("elen",70,"belen");
        student1.displayInfo();
        Student student2=new Student("meni",60,"humeni");
        student2.displayInfo();
        Class c=new Class(student2);
        c.changeGrade(100);
        student2.displayInfo();
        System.out.println(Student.getNumOfStudents());



    }
}
