import static org.junit.jupiter.api.Assertions.*;
public class Ex1Test {
    public static double avg2=0,avg3=0,avg4=0,avg5=0,avg6=0;
    @Test
    public void avg2(){
        avg2=avgCheck(2);
        assertTrue(avg2<8);
    }
    @Test
    public void avg3(){
        avg3=avgCheck(3);
        assertTrue(avg3<9);
    }
    @Test
    public void avg4(){
        avg4=avgCheck(4);
        assertTrue(avg4<10);
    }
    @Test
    public void avg5(){
        avg5=avgCheck(5);
        assertTrue(avg5<10);
    }
    @Test
    public void avg6(){
        avg6=avgCheck(6);
        assertTrue(avg6<10);
        PrintAverage();
    }

    /** avgCheck:
     * Checks the average of the rounds of 100 games.
     * @param numDigits The number of digits in the code
     * @return the average of the rounds.
     */
    public double avgCheck(int numDigits){
        Ex1.numOfDigits=numDigits;             // global int that represent the number of digits in the code
        for(int i=0;i<100;i++){                //loop over the main in Ex1 100 times to get an average rounds number.
            Ex1.main(null);
        }
        double avg=(double) Ex1.counter/100;   //Divide the counter to get the average of 100 runs
        Ex1.counter=0;                         //Reset the counter so we could use it again later
        return avg;
    }

    /**PrintAverage:
     * Prints to guesses average for each digit and for all together
     */
    public static void PrintAverage() {
        System.out.println("the averages are:");
        System.out.println("2) " + avg2 + " 3) " + avg3 + " 4) " + avg4 + " 5) " + avg5 + " 6) " + avg6);
        double allAvgs=(avg2+avg3+avg4+avg5+avg6)/5;
        allAvgs=Math.round(allAvgs*100.0)/100.0;           //round the avarge to 2 digits after the dot
        System.out.println("Average all: "+allAvgs);
    }
}
