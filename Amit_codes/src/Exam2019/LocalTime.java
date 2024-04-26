package Exam2019;

public class LocalTime {

    private String cityName;
    private int ratio;
    private Clock time;

    public LocalTime(String cityName, int ratio, Clock time)
    {
        this.cityName=cityName;
        if (ratio<=12||ratio>=-12)
        {
            this.ratio=ratio;
        }
        else this.ratio=0;
        this.time=new Clock(time);
    }
    public LocalTime(LocalTime l)
    {
       this(l.cityName, l.ratio, l.time);

    }

    public String toString()
    {
        return " "+cityName +','+time+','+ratio;
    }

}
