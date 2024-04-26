package SimBMoedA2023;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Q1Test {

    @Test
    void maxDistPrimes() {
         int n1=Q1.maxDistPrimes(10,19);
         assertEquals(4,n1);
         int n2=Q1.maxDistPrimes(10,11);
         assertEquals(0,n2);
        int n3=Q1.maxDistPrimes(10,10);
        assertEquals(0,n2);
    }
}