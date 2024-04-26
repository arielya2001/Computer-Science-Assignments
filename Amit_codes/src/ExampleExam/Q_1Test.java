package ExampleExam;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Q_1Test {

    @Test
    void d_range()
    {

        int [] res=Q_1.d_range(5);
        int [] res2=Q_1.d_range(8);

        assertArrayEquals(new int[0],res);
        assertArrayEquals(new int[]{8,9,10},res2);




    }
}