package Exam2020;

public class RingCollection {

    private Ring[] rings;
    private int size ,currentIndex;
    final static int defaultSize=3;

    public RingCollection()
    {
        this.rings=new Ring[defaultSize];
        this.size=0;
        this.currentIndex = 0;
    }
    public int contains(Ring t)
    {
        for (int i = 0; i < rings.length; i++)
        {
            if (rings[i].equals(t))
                return i;

        }
        return -1;
    }

    public void addDifferent(Ring t) {
        if (contains(t) == -1) {
            if (currentIndex >= size)
            {
                resize();
            }
            rings[currentIndex++]=t;

        }
    }


    public void resize()
    {
        Ring[]newRings=new Ring[currentIndex];
        for (int i = 0; i < size; i++)
        {
            newRings[i]=rings[i];
        }
        rings=newRings;

    }

    public void remove(int index)
    {
        Ring [] r=new Ring[rings.length-1];
        for (int i = 0; i < index; i++)
        {
            r[i]=rings[i];
        }
        for (int i = index+1; i <rings.length; i++)
        {
            r[i-1]=rings[i];

        }
        rings=r;
    }
}
