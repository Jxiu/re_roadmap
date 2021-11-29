package lc101.sort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class BasicSort {

    public static void main(String[] args) {
        int[] a = new int[]{7,3,2,9,8,3,2,4,7};
        Arrays.sort(a);
    }

    /**
     * 生成随机测试数据
     * @param arrayNum 测试数据数量
     * @param arrayLength 测试数据数组长度
     * @return 指定长度测试数组集合
     */
    public static int[][]generateTestDta(int arrayNum, int arrayLength){
        if(arrayNum <= 0){
            return new int[0][0];
        }
        int[][] a = new int[arrayNum][arrayLength];
        for (int i = 0; i < arrayNum; i++) {
            a[i] = generateTestDta(arrayLength);
        }
        return a;
    }

    /**
     * 生成一个指定长度的测试数据
     * @param length 数组长度
     * @return 指定长度的测试数组
     */
    public static int[] generateTestDta(int length){
        if (length <= 0){
            return new int[]{};
        }
        int[] a = new int[length];
        for (int i = 0; i < length; i++) {
            a[i] = ThreadLocalRandom.current().nextInt(length);
        }
        return a;
    }

    /**
     * 交换数组中两个指定下标的数据
     * @param a 数组
     * @param x 下标x
     * @param y 下标y
     */
    public static void swap(int[] a, int x, int y){
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }
    
    public static class HeapSort{
        /**
         * 升序排列数组
         * @param a
         */
        public static void sort(int[] a){
            // 构建一个最大堆
            int lastIdx = a.length - 1;// 最后一个元素下标
            int beginIdx = (a.length-1)/2;
            for(int i = beginIdx;i >= 0; i--){
                sink(a,i, lastIdx);
            }            // 排序
            while (lastIdx >= 0){
                // 将最大堆中的最大值交换到堆尾部
                swap(a, 0, lastIdx--);
                // 调整新的堆（长度减少后的）
                sink(a, 0, lastIdx);
            }
            
        }


        /**
         * 下沉，将当前节点下沉到适当的子节点位置
         * @param a 数组
         * @param i 当前节点（需要下沉节点）下标
         * @param lastIdx
         */
        private static void sink(int[] a, int i, int lastIdx) {
            while (2*i < lastIdx){
                // 计算如果存在两个子节点计算出较大节点坐标
                int leftIdx = 2*i+1; // 左节点
                int rightIdx = 2*i+2; // 右节点
                int maxIdx = leftIdx; // 默认最大节点是左节点
                if (rightIdx <= lastIdx && a[leftIdx] < a[rightIdx] ){
                    // 存在右边节点且大于当前节点设置较大节点为又节点
                    maxIdx = rightIdx;
                }
                // 父节点已经是最大的（无需下沉）
                if (a[i] > a[maxIdx]){
                    break;
                }
                // 交换父节点和当前较大节点位置
                swap(a, i, maxIdx);
                // 重置当前节点位置
                i = maxIdx;
            }
        }
    }

    public static class QuickSort{

        /**
         * TODO
         * 双轴快速排序算法
         * @param a
         */
        public static void dualPivotSort(int[] a){

        }

        /**
         * 经典快速排序算法，将数组中的数据分为2个区域
         * @param a
         */
        public static void traditionalSort(int[] a){
            traditionalSort(a, 0, a.length-1);
        }


        private static void traditionalSort(int[] a, int lo, int hi){
            // 将数组分为大于a[i]和小于a[i]两部分 i为随机的一个在lo和hi之间的一个下标
            if (lo >= hi){
                // 数组长度为1直接返回
                return;
            }
            int pidx = partition(a, lo, hi);
            traditionalSort(a,lo,pidx-1);
            traditionalSort(a,pidx+1,hi);
        }


        public static int partition(int[] a, int lo, int hi){
            // 将数组分为a[lo...i-1], a[i], a[i+1...hi]
            int i = lo, j = hi + 1, key = a[lo];
            while(true){
                // 从第二个开始向后找到比a[lo]大的数字的下标
                while (a[++i] < key){
                    if (i == hi){
                        break;
                    }
                }
                // 从第最后一个开始找到比a[lo]大的数字的下标
                while(a[--j] > key){
                    if (j == lo){
                        break;
                    }
                }
                // 终止条件
                if (i >= j){
                    break;
                }
                // 交换元素
                swap(a,i,j);
            }
            swap(a,lo,j);// 将key放到正确的位置
            return j;
        }

        public static int partition_error(int[] a, int lo, int hi){
            int l = lo, r = hi, key = a[l];
            while (l < r){
                while (l < r && a[l] <= key){
                    l++;
                }
                while(l < r && a[r] >= key){
                    r--;
                }
                swap(a, l, r);
            }
            swap(a,lo, l);
            return  l;
        }


        /**
         * [4, 6, 2, 5, 8, 1, 1, 9]
         * ------------------------
         * [1, 6, 2, 5, 8, 1, 1T, 9] tempIdx = 6
         * [1, 6T, 2, 5, 8, 1, 6, 9] tempIdx = 0
         * [1, 1, 2, 5, 8, 1T, 6, 9] tempIdx = 0
         * [1, 1, 2, 5T, 8, 5, 6, 9] tempIdx = 0
         * [1, 1, 2, 5, 8, 5, 6, 9]
         * [1, 1, 2, 5, 8, 5, 6, 9]
         * ------------------------
         * [1, 1, 2, 4, 8, 5, 6, 9]
         *
         * [t, 6, 2, 5, 8, 1, 1, 9]
         * [1, 6, 2, 5, 8, 1, t, 9] tempIdx=6
         * [1, t, 2, 5, 8, 1, 6, 9] tempIdx=1
         * [1, 1, 2, 5, 8, 1, 6, 9] tempIdx=5
         * [1, 1, 2, 5, 8, 5, 6, 9] tempIdx=3
         * [1, 1, 2, 5, 8, 5, 6, 9] tempIdx=3
         * [1, 1, 2, 5, 8, 5, 6, 9] tempIdx=3
         * ------------------------
         * [1, 1, 2, 4, 8, 5, 6, 9]
         * @param a
         * @param lo
         * @param hi
         * @return
         */
        public static int partition_i_debug(int[] a, int lo, int hi){
            // key为分区元素
            int tempIdx = lo;
            int key = a[tempIdx];
            // 不断的将大于key的元素和小于key的元素交换
            while (lo < hi){
                // 从后向前找到第一个比key小的
                while (a[hi] > key && lo < hi){
                    hi--;
                }
                // 比key小的元素放到与找到的
                a[tempIdx] = a[hi];
                tempIdx = hi;
                System.out.println(Arrays.toString(a) + " tempIdx=" + tempIdx);
                // 从前向后找到比key大的
                while(a[lo] <= key && lo < hi){
                    lo ++;
                }
                // 将比key大的元素
                a[tempIdx] = a[lo];
                tempIdx = lo;
                System.out.println(Arrays.toString(a) + " tempIdx=" + tempIdx);
            }
            // 交换第一个
            a[tempIdx] = key;
            return lo;
        }

        public static int partition_i(int[] a, int lo, int hi){
            // key为分区元素
            int key = a[lo];
            // 不断的将大于key的元素和小于key的元素交换
            while (lo < hi){
                // 从后向前找到第一个比key小的
                while (a[hi] > key && lo < hi){
                    hi--;
                }
                // 比key小的元素放到与找到的
                a[lo] = a[hi];
//                System.out.println(Arrays.toString(a));
                // 从前向后找到比key大的
                while(a[lo] <= key && lo < hi){
                    lo ++;
                }
                // 将比key大的元素
                a[hi] = a[lo];
//                System.out.println(Arrays.toString(a));
            }
            // 交换第一个
            a[lo] = key;
            return lo;
        }

    }
}
