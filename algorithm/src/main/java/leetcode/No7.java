package leetcode;

/**
 * <p>
 *     给你一个 32 位的有符号整数 x ，返回 x 中每位上的数字反转后的结果。如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 * </p>
 * @author jxiu
 * @date 2021/2/5
 */
public class No7 {


    static class Solution {
        public static void main(String[] args) {
            System.out.println(reverse(12345));
            System.out.println(reverse(-12345));
            System.out.println(reverse(Integer.MAX_VALUE));
            System.out.println(reverse(Integer.MAX_VALUE-6));
            System.out.println(reverse(Integer.MIN_VALUE));
            System.out.println(reverse(Integer.MIN_VALUE + 36));
        }
        public static int reverse1(int x) {
            int rev = 0;
            while(x != 0){
                int pop = x%10;
                x /=10;
                if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE && pop>7)) return 0;
                if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE && pop<-8)) return 0;
                rev = rev*10 + pop;
            }
            return rev;
        }

        /**
         * 利用整数溢出
         * @param x
         * @return
         */
        public static int reverse(int x) {
            int rev = 0;
            while(x != 0){
                int tmp = rev;
                rev = rev * 10 + x%10;
                if (rev/10 != tmp){
                    return 0;
                }
                x=x/10;
            }
            return rev;
        }
    }

}

