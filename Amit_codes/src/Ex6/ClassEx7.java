package Ex6;

public class ClassEx7 {
    private Student [] students=new Student[3];
    public ClassEx7(Student student1,Student student2,Student student3)
    {
        students[0]=student1;
        students[1]=student2;
        students[2]=student3;

    }
    public void changeGrade(int grade,String name) {

        for (int i = 0; i < students.length; i++) {
            if (students[i].getName().equals(name))
            students[i].setMolecularBiologyGrade(grade);
        }
    }




    public static void main(String[]args)
    {
        System.out.println(Student.getNumOfStudents());
        Student student1=new Student("elen",70,"belen");
        student1.displayInfo();
        Student student2=new Student("meni",60,"humeni");
        student2.displayInfo();
        student2.displayInfo();
        System.out.println(Student.getNumOfStudents());



    }
}
