package Exam2019;

public class WorldTimes {

    private Clock[] arr;
    private int numOfClocks;
    private static int counter = 0;

    public WorldTimes(int n) {
        this.numOfClocks=n;
        arr = new Clock[numOfClocks];
        for (int i = 0; i < n; i++) {
            arr[i] = new Clock(i, 0);
        }
        counter++;
    }

    public WorldTimes(WorldTimes w) {
        this.numOfClocks = w.numOfClocks;
        arr = new Clock[w.numOfClocks];
        for (int i = 0; i < numOfClocks; i++) {
            arr[i] = new Clock(w.getTimeIndex(i));

        }
        counter++;
    }

        public Clock getTimeIndex(int i)
        {
            if (arr==null||i<0||i>numOfClocks)
                return null;
            return arr[i];
        }

        public void addOne()
        {
            for (int i = 0; i < numOfClocks; i++)
            {
               arr[i].setHours(arr[i].getHours()+1);
            }
        }
        public static int getNumOfObject() {
            return counter;
        }




    }


