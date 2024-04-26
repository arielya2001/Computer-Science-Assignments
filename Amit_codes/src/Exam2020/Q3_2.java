package Exam2020;

public class Q3_2 {
    public static void main(String[] args) {
        int[] a = {2, 1, 2};
        int[] b = {5, 1, 2, 3, 2, 1, 2, 1, 2};
        System.out.println(isIn(a,b));

    }
    public static int isIn(int[] a, int[] b) {
        int counter = 0;
        for (int i = 0; i < b.length-a.length+1; i++) {
            if (a[0] == b[i])
            {
                for (int j = 0; j < a.length; j++) {
                    if (a[j] != b[i+j]) {
                        break;
                    }
                    if (j==a.length-1)
                    {
                        counter++;
                    }

                }

            }
        }
        return counter;
    }
}

