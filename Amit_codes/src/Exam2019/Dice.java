package Exam2019;

public class Dice {
    private int num;
    private int[] arrNum;

    public Dice(int num)
    {
        this.num=num;
        this.arrNum=new int[num];
        for (int i=0;i<num;i++)
        {
            arrNum[i]=(int)(Math.random()*2*num)+1;
        }
    }

    public Dice(Dice d)
    {
        this.num=d.num;
        this.arrNum=new int[d.arrNum.length];
        for (int i=0;i<d.arrNum.length;i++)
        {
            arrNum[i]=d.arrNum[i];
        }
    }

    public int throw_()
    {
        int r=(int)Math.random()*this.num;
        return arrNum[r];
    }
}
