import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Ex1Test {
    /** -test_2_Digits-
     * Calculates the average number of rounds required by the Ex1.main method over 100 runs for 2 digits.
     * Prints the
     */
    @Test
    public void test_2_Digits() {
        double avg = Calculate_avg_rounds(2);
        System.out.println("The average for 2 digits is: " + avg);
        assertTrue(avg < 10);
    }
    /** -test_3_Digits-
     * Calculates the average number of rounds required by the Ex1.main method over 100 runs for 3 digits.
     */

    @Test
    public void test_3_Digits() {
        double avg = Calculate_avg_rounds(3);
        System.out.println("The average for 3 digits is: " + avg);
        assertTrue(avg < 10);
    }
    /** -test_4_Digits-
     * Calculates the average number of rounds required by the Ex1.main method over 100 runs for 4 digits.
     */

    @Test
    public void test_4_Digits() {
        double avg = Calculate_avg_rounds(4);
        System.out.println("The average for 4 digits is: " + avg);
        assertTrue(avg < 10);
    }
    /** -test_5_Digits-
     * Calculates the average number of rounds required by the Ex1.main method over 100 runs for 5 digits.
     */

    @Test
    public void test_5_Digits() {
        double avg = Calculate_avg_rounds(5);
        System.out.println("The average for 5 digits is: " + avg);
        assertTrue(avg < 10);
    }
    /** -test_6_Digits-
     * Calculates the average number of rounds required by the Ex1.main method over 100 runs for 6 digits.
     */

    @Test
    public void test_6_Digits() {
        double avg = Calculate_avg_rounds(6);
        System.out.println("The average for 6 digits is: " + avg);
        assertTrue(avg < 10);
    }

    /** -Calculate_avg_rounds-
     * Computes the average number of rounds needed by the Ex1.main method across 100 iterations.
     * @param numOfDigits The number of digits utilized in the secret code for Ex1.main method
     * @return The average number of rounds completed during 100 iterations of Ex1.main method
     */

    public double Calculate_avg_rounds(int numOfDigits) {
        Ex1.numOfDigits = numOfDigits;
        int total = 0; // sum the total rounds, initialized as zero.
        for (int i = 0; i < 100; i++) {
            Ex1.main(null); // Calling for the main function in Ex1
            total += Ex1.rounds; // Adding to total the sum of numbers, for this round
            Ex1.rounds = 0; // Resetting after each round.
        }
        return (double) total / 100; // return the average of rounds for 100 iterations.
    }
}
