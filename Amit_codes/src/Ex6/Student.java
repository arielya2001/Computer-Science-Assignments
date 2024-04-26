package Ex6;

public class Student {
    private String name;
    private int molecularBiologyGrade;
    private String address;
    private static int numOfStudents;

    public Student(String name,int molecularBiologyGrade,String address)
    {
        this.name=name;
        this.molecularBiologyGrade=molecularBiologyGrade;
        this.address=address;
        numOfStudents++;

    }
    public void setMolecularBiologyGrade(int molecularBiologyGrade)
    {
        if (molecularBiologyGrade<0)
            throw new IllegalArgumentException("grade cannot be under 0");
        this.molecularBiologyGrade=molecularBiologyGrade;
    }
    public void displayInfo()
    {
        System.out.println("name: "+name);
        System.out.println("grade: "+molecularBiologyGrade);
        System.out.println("address: "+address);

    }
    public String  getName()
    {
        return name;
    }
    public static int getNumOfStudents()
    {
        return numOfStudents;
    }

public static void main(String[]args)
{
    Student student1=new Student("elen",70,"belen");
    student1.displayInfo();
    Student student2=new Student("meni",60,"humeni");
    student2.displayInfo();

}

}

