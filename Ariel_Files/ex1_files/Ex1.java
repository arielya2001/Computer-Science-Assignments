import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

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
 * Average required guesses 2: 6.71
 * Average required guesses 3: 7.81
 * Average required guesses 4: 8.25
 * Average required guesses 5: 8.89
 * Average required guesses 6: 9.35
 *
 */
public class Ex1 {
    public static final String Title = "Ex1 demo: manual Bulls & Cows game";
    public static int numOfDigits = 5;
    public static int counter = 0;

    public static void main(String[] args) {
        BP_Server game = new BP_Server();   // Starting the "game-server"
        long myID = 318727187;             // Your ID should be written here
        game.startGame(myID, numOfDigits);  // Starting a game
        System.out.println(Title+" with code of "+numOfDigits+" digits");
        //manualEx1Game(game);
        autoEx1Game(game); // you should implement this function) and any additional required functions).
    }
    public static void manualEx1Game(BP_Server game) {
        Scanner sc = new Scanner(System.in);
        int ind=1;      // Index of the guess
        int numOfDigits = game.getNumOfDigits();
        double max = Math.pow(10,numOfDigits);
        while(game.isRunning()) {           // While the game is running (the code has not been found yet
            System.out.println(ind+") enter a guess: ");
            int g = sc.nextInt();
            if(g>=0 && g<max) {
                int[] guess = toArray(g, numOfDigits); // int to digit array
                int[] res = game.play(guess); // Playing a round and getting the B,C
                if (game.isRunning()) {     // While the game is running
                    System.out.println(ind + ") B: " + res[0] + ",  C: " + res[1]); // Prints the Bulls [0], and the Cows [1]
                    ind += 1;               // Increasing the index
                }
            }
            else {
                System.out.println("ERR: wrong input, try again");
            }
        }
        System.out.println(game.getStatus());
    }


    /**
     * Simple parsing function that gets an int and returns an array of digits
     * @param a - a natural number (as a guess)
     * @param size  - number of digits (to handle the 00 case).
     * @return an array of digits
     */
    private static int[] toArray(int a, int size){
        int[] c = new int[size];
        for(int j=0;j<c.length;j+=1) {
            c[j] = a%10;
            a=a/10;
        }
        return c;
    }
////////////////////////////////////////////////////
    /**
     * This function solves the Bulls & Cows game automatically.
     * You should implement
     * **** Add a specific explanation for each function ****
     *
     */
    public static void autoEx1Game(BP_Server game) {
        int ind = 1; // Index of the guess
        int numOfDigits = game.getNumOfDigits();

        ArrayList<Integer> possibleGuesses = generateAllPossibleGuesses(numOfDigits);

        while (game.isRunning() && ind <= 10) {
            counter+=1;
            int g = selectGuess(possibleGuesses);
            int[] guess = toArray(g, numOfDigits);
            int[] res = game.play(guess);
            System.out.print("Guess: " + Arrays.toString(guess) + ", B: " + res[0] + ",  C: " + res[1] + "\n");
            ind += 1;

            // Filter out possibilities based on the result
            filterPossibleGuesses(possibleGuesses, guess, res);
        }
    }

    private static int selectGuess(ArrayList<Integer> possibleGuesses) {
        // For simplicity, you can implement a more sophisticated strategy here.
        // This example selects the first remaining guess.
        return possibleGuesses.get(0);
    }

    private static void filterPossibleGuesses(ArrayList<Integer> possibleGuesses, int[] guess, int[] result) {
        possibleGuesses.removeIf(code -> !Arrays.equals(calculateBullsAndCows(toArray(code, guess.length), guess), result));
    }

    private static int generateGuess(int numOfDigits, double max) {
        // Implement your logic to generate a random guess here
        // This is a placeholder, you can replace it with a better algorithm
        return (int) (Math.random() * max);
    }

    private static boolean isAllZeros(int[] array) {
        return array != null && Arrays.stream(array).allMatch(num -> num == 0);
    }

    private static void generateAllPossibleCodes(ArrayList<String> codes, int numOfDigits) {
        int maxNum = (int) Math.pow(10, numOfDigits);
        for (int i = 0; i < maxNum; i++) {
            codes.add(String.format("%0" + numOfDigits + "d", i));
        }
    }
    private static ArrayList<Integer> generateAllPossibleGuesses(int numOfDigits) {
        ArrayList<Integer> possibleGuesses = new ArrayList<>();
        int maxNum = (int) Math.pow(10, numOfDigits);

        for (int i = 0; i < maxNum; i++) {
            possibleGuesses.add(i);
        }

        return possibleGuesses;
    }



    private static void filterPossibleCodes(ArrayList<String> codes, int[] guess, int[] result) {
        codes.removeIf(code -> !Arrays.equals(testBullsNCows(stringToArray(code), guess, result), result));
    }

    private static String getMaxScoreGuess(ArrayList<String> codes, int numOfDigits) {
        String bestGuess = "";
        int minMaxScore = Integer.MAX_VALUE;

        for (String guess : codes) {
            int maxScore = 0;

            for (String code : codes) {
                int[] score = calculateBullsAndCows(stringToArray(guess), stringToArray(code));
                maxScore = Math.max(maxScore, Math.min(score[0], score[1]));
            }

            if (maxScore < minMaxScore) {
                minMaxScore = maxScore;
                bestGuess = guess;
            }
        }

        return bestGuess;
    }

    private static int[] calculateBullsAndCows(int[] guess, int[] code) {
        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < guess.length; i++) {
            if (guess[i] == code[i]) {
                bulls++;
            } else if (containsDigit(code, guess[i])) {
                cows++;
            }
        }

        return new int[]{bulls, cows};
    }

    private static boolean containsDigit(int[] array, int digit) {
        for (int value : array) {
            if (value == digit) {
                return true;
            }
        }
        return false;
    }

    private static int[] testBullsNCows(int[] code1, int[] code2, int[] result) {
        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < code1.length; i++) {
            if (code1[i] == code2[i]) {
                bulls++;
            } else if (containsDigit(code2, code1[i])) {
                cows++;
            }
        }

        return new int[]{bulls, cows};
    }

    private static int[] stringToArray(String str) {
        int[] array = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            array[i] = Character.getNumericValue(str.charAt(i));
        }
        return array;
    }




}









