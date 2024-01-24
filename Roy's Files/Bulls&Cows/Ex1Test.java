import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Ex1Test {

    @Test
    public void test2Digits() {
        double avg = testAverageRounds(2);
        System.out.println("Average for 2 digits: " + avg);
    }

    @Test
    public void test3Digits() {
        double avg = testAverageRounds(3);
        System.out.println("Average for 3 digits: " + avg);
    }

    @Test
    public void test4Digits() {
        double avg = testAverageRounds(4);
        System.out.println("Average for 4 digits: " + avg);
    }

    @Test
    public void test5Digits() {
        double avg = testAverageRounds(5);
        System.out.println("Average for 5 digits: " + avg);
    }

    @Test
    public void test6Digits() {
        double avg = testAverageRounds(6);
        System.out.println("Average for 6 digits: " + avg);
    }
    public double testAverageRounds(int numOfDigits) {
        Ex1.numOfDigits = numOfDigits;
        int totalRounds = 0; // sum the total of all rounds in 100 runs
        for (int i = 0; i < 100; i++) {
            Ex1.main(null);
            totalRounds += Ex1.rounds_counter; // Add to sum the number of rounds from this run
            Ex1.rounds_counter = 0; // Reset counter after each run
        }
        return (double) totalRounds / 100; // return the avg for 100 runs
    }
}
