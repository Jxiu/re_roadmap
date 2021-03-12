package leetcode;

import java.util.Arrays;

/**
 * 数组中的第k最大元素（排序后第k个元素）
 * <p>
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * </p>
 * <p>
 * <pre>
 *         输入: [3,2,1,5,6,4] 和 k = 2
 *         输出: 5
 *     </pre>
 * </p>
 *
 * @author jxiu
 * @date 2021/2/23
 */
public class No215 {

    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 5, 6, 4};
        System.out.println(Arrays.toString(arr));
        int k = findKthLargest1(arr, 2);
        System.out.println(k);
        int[] arr1 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println(Arrays.toString(arr1));
        k = findKthLargest(arr1, arr1.length);
        System.out.println(k);
    }

    /**
     * 使用堆排序
     * <p>
     * 构建一个长度为k的最小堆
     * </p>
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        if (nums == null) {
            throw new IllegalArgumentException("数组为空");
        }
        if (k < 1 || k > nums.length) {
            throw new IllegalArgumentException("参数异常");
        }
        // 以前k个元素构建一个最小堆
        makeHeap(nums, k);
        // 遍历其余元素
//        for (int i = k; i < nums.length; i++){
//            if (nums[i] > nums[0]){
//                swap(nums,i , 0);
//                adjustHeap(nums,i, k);
//            }
//        }
        // 如果最小元素小于当前元素交换元素，调整堆
        return nums[0];
    }

    /**
     * 创建最小堆
     *
     * @param nums
     * @param k
     */
    private static void makeHeap(int[] nums, int k) {
        for (int i = k / 2 - 1; i > 0; i--) {
            adjustHeap(nums, i, k);
        }
    }

    private static void adjustHeap(int[] nums, int i, int length) {
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            if (k + 1 < length && nums[k] > nums[k + 1]) {
                k++;
            }
            if (nums[k] < nums[i]) {
                swap(nums, i, k);
                i = k;
            } else {
                break;
            }
        }
    }

    /**
     * 交换元素
     *
     * @param array
     * @param a
     * @param b
     */
    private static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    /**
     * 排序
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest1(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}
