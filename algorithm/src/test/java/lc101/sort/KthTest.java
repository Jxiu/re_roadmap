package lc101.sort;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class KthTest {

    @ParameterizedTest
    @MethodSource("getSortTestData")
    public void test(int[] a){
//        int[] a = new int[]{0, 2, 2, 2, 4, 5, 5, 6, 7, 8};
        System.out.println("array:" + Arrays.toString(a));

        int k = 3;
        int kth = new Kth().findK(a,k);

        Arrays.sort(a);
        System.out.println("sorted array:" + Arrays.toString(a));
        System.out.println("kth: " + kth + ", sort kth : " +  a[k-1]);
        assertEquals(kth, a[k-1]);
    }

    @Test
    public void test(){
        int[] a = new int[]{8, 9, 1, 8, 9, 2, 2, 0, 7, 2};
        int k = 3;
        int kth = new Kth().findK(a,k);

        Arrays.sort(a);
        System.out.println("sorted array:" + Arrays.toString(a));
        System.out.println("kth: " + kth + ", sort kth : " +  a[k-1]);
        assertEquals(kth, a[k-1]);
    }


    public static int[][] getSortTestData(){
        return BasicSort.generateTestDta(10,10);
    }
}