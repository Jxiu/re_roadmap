package lc101.sort;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class BasicSortTest {


    @ParameterizedTest
    @MethodSource("getSortTestData")
    public void mergeSort(int[] a){
        int[] copy = Arrays.copyOf(a,a.length);

        BasicSort.MergeSort.sort(a);
        System.out.println(Arrays.toString(a));

        Arrays.sort(copy);
        System.out.println(Arrays.toString(copy));
        assertArrayEquals(a, copy);
    }


    @ParameterizedTest
    @MethodSource("getSortTestData")
    public void insertionSort(int[] a){
        int[] copy = Arrays.copyOf(a,a.length);

        BasicSort.InsertionSort.sort(a);
        System.out.println(Arrays.toString(a));

        Arrays.sort(copy);
        System.out.println(Arrays.toString(copy));
        assertArrayEquals(a, copy);
    }


    @ParameterizedTest
    @MethodSource("getSortTestData")
    public void heapSort(int[] a){
        int[] copy = Arrays.copyOf(a,a.length);

        BasicSort.HeapSort.sort(a);
        System.out.println(Arrays.toString(a));

        Arrays.sort(copy);
        assertArrayEquals(a, copy);
    }


    public static int[][] getSortTestData(){
//        return BasicSort.generateTestDta(3,10);
        int [][] a = {{4,6,2,5,8,1,1,9}};
        return  a;
    }

    @Test
    public void swap(){
        int[] arr = new int[]{4,6};
        BasicSort.swap(arr, 0,1);
        assertArrayEquals(arr, new int[]{6,4});
    }

    @Test
    public void quickSort_dualPivotSort(){
        int[] arr = new int[]{4,6,2,5,8,1,1,9};
        int[] copy = Arrays.copyOf(arr, arr.length);

        BasicSort.QuickSort.traditionalSort(arr);

        System.out.println(Arrays.toString(arr));

        Arrays.sort(copy);
        System.out.println(Arrays.toString(copy));

        assertArrayEquals(arr, copy);

    }

    @Test
    public void quickSort_partition(){
        int[] arr = new int[]{4,6,2,5,8,1,1,9};
        int[] arr_debug = Arrays.copyOf(arr, arr.length);

        BasicSort.QuickSort.partition_i(arr, 0, arr.length-1);
        System.out.println();
        System.out.println(Arrays.toString(arr));
        System.out.println("=======================");

        BasicSort.QuickSort.partition_i_debug(arr_debug, 0, arr.length-1);
        System.out.println();
        System.out.println(Arrays.toString(arr));

    }
}