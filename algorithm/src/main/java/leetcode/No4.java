package leetcode;

public class No4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int mid = (n + m)>>1;
        int mod = (n + m)%2;

        if (mod == 0){
            mid = 0;
        }
        //两个数组有序
        if (nums1[m-1] <= nums2[0]){
            // 奇数
            return getOrderedMid(nums1, nums2, mid, mod);
        }else if (nums2[n-1] <= nums2[0]){
            return getOrderedMid(nums2, nums1, mid, mod);
        }else {
            //两个数组无序
            int mm = m/2;
            int nm = n/2;
            if (mm < nm){

            }
        }

        return 0;

    }

    private double getOrderedMid(int[] le, int[] ge, int mid, int mod) {
        if (mod == 1){
            return getM(le, ge, mid +1);
        }else {
        // 偶数
            int lem = getM(le, ge, mid);
            int bem = getM(le, ge, mid +1);
            return ((double) (lem+bem))/2;
        }
    }

    /**
     *
     * @param le 较小数组
     * @param ge 较大数组
     * @param m 位置
     * @return
     */
    private int getM(int[] le, int[] ge, int m) {
        if (m <= le.length){
            return le[m-1];
        }else{
            return ge[(m-1-le.length)];
        }
    }
}
