import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) //Sorting the test functions in ascending order by names

public class Ex1Test {
    public static double avg2=0,avg3=0,avg4=0,avg5=0,avg6=0;
    /** -test_digits_2-
     * Calculates the average number of rounds required by the Ex1.main method over 100 runs for 2 digits.
     * Prints the
     */
    @Test
    public void test_digits_2() {
        avg2 = Calculate_avg_rounds(2);
        System.out.println("The average for 2 digits is: " + avg2);
        assertTrue(avg2 < 10);
    }
    /** -test_digits_3-
     * Calculates the average number of rounds required by the Ex1.main method over 100 runs for 3 digits.
     */

    @Test
    public void test_digits_3() {
        avg3 = Calculate_avg_rounds(3);
        System.out.println("The average for 3 digits is: " + avg3);
        assertTrue(avg3 < 10);
    }
    /** -test_digits_4-
     * Calculates the average number of rounds required by the Ex1.main method over 100 runs for 4 digits.
     */

    @Test
    public void test_digits_4() {
        avg4 = Calculate_avg_rounds(4);
        System.out.println("The average for 4 digits is: " + avg4);
        assertTrue(avg4 < 10);
    }
    /** -test_digits_5-
     * Calculates the average number of rounds required by the Ex1.main method over 100 runs for 5 digits.
     */

    @Test
    public void test_digits_5() {
        avg5 = Calculate_avg_rounds(5);
        System.out.println("The average for 5 digits is: " + avg5);
        assertTrue(avg5 < 10);
    }
    /** -test_digits_6-
     * Calculates the average number of rounds required by the Ex1.main method over 100 runs for 6 digits.
     */

    @Test
    public void test_digits_6() {
        avg6 = Calculate_avg_rounds(6);
        System.out.println("The average for 6 digits is: " + avg6);
        assertTrue(avg6 < 10);
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

    /** -test_digits_All-
     * Computes and prints the average of all the test functions(2-6).
     * Calculates the sum of the averages and prints each average along with its corresponding index in a single line using a loop.
     * Finally, it calculates the overall average and prints it with two digits after the decimal point.
     */
    @Test
    public void test_digits_All() {
        double[] avgs = {avg2, avg3, avg4, avg5, avg6}; // Store the averages in an array
        double sum_avg = 0; // Initialize sum of averages
        System.out.print("The averages are: "); // Print statement for averages
        for (int i = 0; i < avgs.length; i++) {
            System.out.printf("%d) %.2f ", i + 2, avgs[i]); // Print each average with index
            sum_avg += avgs[i]; // Adding each average to sum of averages
        }
        sum_avg /= avgs.length; // Calculate sum average
        System.out.printf("%nAverage all: %.2f%n", sum_avg); // Print sum average

    }
}
