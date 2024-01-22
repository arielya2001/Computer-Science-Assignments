import java.util.Arrays;
import java.util.Scanner;
/**
 * Introduction to Computer Science, Ariel University, Ex1 (manual Example + a Template for your solution)
 * See: https://docs.google.com/document/d/1C1BZmi_Qv6oRrL4T5oN9N2bBMFOHPzSI/edit?usp=sharing&ouid=113711744349547563645&rtpof=true&sd=true
 *
 * Ex1 Bulls & Cows - Automatic solution.
 * **** Add a general readme text here ****
 * Add your explanation here:
 *
 *
 * **** General Solution (algorithm) ****
 * Add your explanation here:
 *
 *
 *
 * **** Results ****
 * Make sure to state the average required guesses
 * for 2,3,4,5,6 digit code:
 *
 * Average required guesses 2: ___
 * Average required guesses 3: ___
 * Average required guesses 4: ___
 * Average required guesses 5: ___
 * Average required guesses 6: ___
 *
 */
public class Ex1 {
    public static final String Title = "Ex1 demo: manual Bulls & Cows game";

    public static void main(String[] args) {
        BP_Server game = new BP_Server();   // Starting the "game-server"
        long myID = 123456789L;             // Your ID should be written here
        int numOfDigits = 3;                // Number of digits [2,6]
        game.startGame(myID, numOfDigits);  // Starting a game
        System.out.println(Title + " with code of " + numOfDigits + " digits");
        //manualEx1Game(game);
        autoEx1Game(game); // you should implement this function )and any additional required functions).
    }

    public static void manualEx1Game(BP_Server game) {
        Scanner sc = new Scanner(System.in);
        int ind = 1;      // Index of the guess
        int numOfDigits = game.getNumOfDigits();
        double max = Math.pow(10, numOfDigits);
        while (game.isRunning()) {           // While the game is running (the code has not been found yet
            System.out.println(ind + ") enter a guess: ");
            int g = sc.nextInt();
            if (g >= 0 && g < max) {
                int[] guess = toArray(g, numOfDigits); // int to digit array
                int[] res = game.play(guess); // Playing a round and getting the B,C
                if (game.isRunning()) {     // While the game is running
                    System.out.println(ind + ") B: " + res[0] + ",  C: " + res[1]); // Prints the Bulls [0], and the Cows [1]
                    ind += 1;               // Increasing the index
                }
            } else {
                System.out.println("ERR: wrong input, try again");
            }
        }
        System.out.println(game.getStatus());
    }


    /**
     * Simple parsing function that gets an int and returns an array of digits
     *
     * @param a    - a natural number (as a guess)
     * @param size - number of digits (to handle the 00 case).
     * @return an array of digits
     */
    private static int[] toArray(int a, int size) {
        int[] c = new int[size];
        for (int j = 0; j < c.length; j += 1) {
            c[j] = a % 10;
            a = a / 10;
        }
        return c;
    }
////////////////////////////////////////////////////

    /**
     * This function solves the Bulls & Cows game automatically.
     * You should implement
     * **** Add a specific explanation for each function ****
     */
    public static void autoEx1Game(BP_Server game) {
        //int roundsCounter = 0;
        int numOfDigits = game.getNumOfDigits();
        int max = (int) Math.pow(10, numOfDigits);
        int[] result = new int[2];
        boolean[] allTheNumbers = new boolean[max];

        for (int b = 0; b < allTheNumbers.length; b++) {
            allTheNumbers[b] = true;
        }

        while (game.isRunning()) {
            int guess = nextGuess(allTheNumbers);

            if (guess == -1) {
                System.out.println("No more valid guesses. Ending the game.");
                break;
            }

            //roundsCounter += 1;
            System.out.println(guess);
            int[] guessArray = toArray(guess, numOfDigits);

            result = game.play(guessArray);

            if (result[0] == numOfDigits) {
                //System.out.println("Code found in " + roundsCounter + " guesses.");
                break;
            }

            allTheNumbers = updateGuess(allTheNumbers, guessArray, result);
        }
    }

    private static int nextGuess(boolean[] possibleNumbers) {
        for (int i = 0; i < possibleNumbers.length; i++) {
            if (possibleNumbers[i]) {
                return i;  // Return the index directly, not the converted guess
            }
        }

        return -1;
    }

    private static boolean[] updateGuess(boolean[] possibleNumbers, int[] guess, int[] result) {
        for (int i = 0; i < possibleNumbers.length; i++) {
            if (possibleNumbers[i]) {
                int[] guessArray = toArray(i, guess.length); // the copy of the guess in array
                int[] arResult = calculateBullsAndCows(guessArray, guess); // the number of bulls and cows that the guess has returned

                if (!Arrays.equals(arResult, result)) {
                    possibleNumbers[i] = false;
                }
            }
        }
        return possibleNumbers;
    }

    private static int[] calculateBullsAndCows(int[] copyGuess, int[] guess) {
        int bulls = countBulls(copyGuess, guess);
        int cows = countCows(copyGuess, guess);

        return new int[]{bulls, cows};
    }

    private static int countBulls(int[] copyGuess, int[] guess) {
        int bulls = 0;

        for (int j = 0; j < guess.length; j++) {
            if (guess[j] == copyGuess[j]) {
                bulls++;
            }
        }

        return bulls;
    }

    private static int countCows(int[] copyGuess, int[] guess) {
        int cows = 0;

        for (int i = 0; i < guess.length; i++) {
            int guessDigit = guess[i];
            for (int j = 0; j < copyGuess.length; j++) {
                int codeDigit = copyGuess[j];

                if (codeDigit == guessDigit && guess[i] != codeDigit) {
                    cows++;
                    break;
                }
            }
        }

        return cows;
    }
}

