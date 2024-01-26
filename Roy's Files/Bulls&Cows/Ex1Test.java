import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.*;

public class Ex1Test {
    private static double avg2, avg3, avg4, avg5, avg6; // Global variables to store the averages

    @Test
    public void test2Digits() {
        avg2 = testAverageRounds(2);
        System.out.println("Average for 2 digits: " + avg2);
        assertTrue(avg2 < 9.5);
    }

    @Test
    public void test3Digits() {
        avg3 = testAverageRounds(3);
        System.out.println("Average for 3 digits: " + avg3);
        assertTrue(avg3 < 9.5);
    }

    @Test
    public void test4Digits() {
        avg4 = testAverageRounds(4);
        System.out.println("Average for 4 digits: " + avg4);
        assertTrue(avg4 < 9.5);
    }

    @Test
    public void test5Digits() {
        avg5 = testAverageRounds(5);
        System.out.println("Average for 5 digits: " + avg5);
        assertTrue(avg5 < 9.5);
    }

    @Test
    public void test6Digits() {
        avg6 = testAverageRounds(6);
        System.out.println("Average for 6 digits: " + avg6);
        assertTrue(avg6 < 10);
    }

    // This method is called after all tests have been run
    @AfterAll
    public static void printAllAverages() {
        System.out.println("The Averages are:");
        System.out.println("2) " + avg2 + "\n3) " + avg3 + "\n4) " + avg4 + "\n5) " + avg5 + "\n6) " + avg6);
        double overallAvg = (avg2 + avg3 + avg4 + avg5 + avg6) / 5.0;
        System.out.println("Overall Average: " + Math.round(overallAvg * 100.0) / 100.0);
    }

    /** -testAverageRounds-
     * Calculates the average number of rounds required by the Ex1.main method over 100 runs
     * @param numOfDigits The number of digits to be used in the Ex1.main method for the secret code
     * @return The average number of rounds taken by the Ex1.main method over 100 runs
     */
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
