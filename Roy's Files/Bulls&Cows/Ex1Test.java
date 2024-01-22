import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Ex1Test {

    @Test
    public void test2Digits() {
        double avg = testAverageRounds(2);
        System.out.println("Average for 2 digits: " + avg);
        assertTrue(avg > 0); // Example assertion
    }

    @Test
    public void test3Digits() {
        double avg = testAverageRounds(3);
        System.out.println("Average for 3 digits: " + avg);
        assertTrue(avg > 0); // Example assertion
    }

    @Test
    public void test4Digits() {
        double avg = testAverageRounds(3);
        System.out.println("Average for 3 digits: " + avg);
        assertTrue(avg > 0); // Example assertion
    }
    @Test
    public void test5Digits() {
        double avg = testAverageRounds(3);
        System.out.println("Average for 3 digits: " + avg);
        assertTrue(avg > 0); // Example assertion
    }
    @Test
    public void test6Digits() {
        double avg = testAverageRounds(3);
        System.out.println("Average for 3 digits: " + avg);
        assertTrue(avg > 0); // Example assertion
    }
    private double testAverageRounds(int numOfDigits) {
        Ex1.numOfDigits = numOfDigits;
        int totalRounds = 0;
        for (int i = 0; i < 100; i++) {
            Ex1.main(null); // Assuming Ex1.main() runs the game and updates rounds_counter
            totalRounds += Ex1.rounds_counter;
            Ex1.rounds_counter = 0; // Reset counter after each run
        }
        return (double) totalRounds / 100;
    }
}
