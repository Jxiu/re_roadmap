package lc101.sort;

import java.util.Arrays;

/**
 * LC.215
 * <p>
 *     在一个未排序的数组中，找到第 k 大的数字。
 * </p>
 */
public class Kth {

    public int findK(int[] a, int k){
        int kth = quickSelectK(a, k, 0, a.length -1);
        return kth;
    }

    /**
     * 快速选择算法
     * @param a
     * @param k
     * @param lo
     * @param hi
     * @return
     */
    private int quickSelectK(int[] a, int k, int lo, int hi) {
        // 8, 9, 1, 8, 9, 2, 2, 0, 7, 2
        // 随机选择数字， 将数据为大于和小于该数字两部分
        System.out.println(Arrays.toString(a));
        int pIdx = partition(a, lo, hi);
        int kIdx = k-1;
        if (pIdx == kIdx){
            return a[pIdx];
        }if (kIdx < pIdx){
            return quickSelectK(a, k, lo,pIdx-1);
        }else {
            return quickSelectK(a, k, pIdx+1, hi);
        }
    }

    private int partition(int[] a, int lo, int hi) {
        int i = lo, j = hi+1, key = a[lo];
        while(true){
            while (a[++i] < key){
                if (i == hi){
                    break;
                }
            }
            while (a[--j] > key){
                if (j == lo){
                    break;
                }
            }
            if (i >= j){
                break;
            }
            BasicSort.swap(a, i, j);
        }
        BasicSort.swap(a, lo, j);
        return j;
    }
}
