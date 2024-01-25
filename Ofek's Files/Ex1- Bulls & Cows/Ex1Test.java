import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class Ex1Test {
    @Test
    public void avg2(){
        double avg2=avgCheck(2);
        System.out.println("the average in 2 digits is:" +avg2);
        assertTrue(avg2>0);
    }
    @Test
    public void avg3(){
        double avg3=avgCheck(3);
        System.out.println("the average in 3 digits is:" +avg3);
        assertTrue(avg3>0);
    }
    @Test
    public void avg4(){
        double avg4=avgCheck(4);
        System.out.println("the average in 4 digits is:" +avg4);
        assertTrue(avg4>0);
    }
    @Test
    public void avg5(){
        double avg5=avgCheck(5);
        System.out.println("the average in 5 digits is:" +avg5);
        assertTrue(avg5>0);
    }
    @Test
    public void avg6(){
        double avg6=avgCheck(6);
        System.out.println("the average in 6 digits is:" +avg6);
        assertTrue(avg6>0);
    }
    public double avgCheck(int numDigits){
        Ex1.numOfDigits=numDigits;
        for(int i=0;i<100;i++){
            Ex1.main(null);
        }
        double avg=(double) Ex1.counter/100;
        Ex1.counter=0;
        return avg;
    }
}
