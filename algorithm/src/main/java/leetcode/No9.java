package leetcode;

/**
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 ture ；否则，返回 false 。
 * <p>
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
 * <p>
 * <pre>
 *         输入：x = 121
 *         输出：true
 *     </pre>
 * <pre>
 * 输入：x = -121
 * 输出：false
 * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 *     </pre>
 * <pre>
 *         输入：x = 10
 *         输出：false
 *         解释：从右向左读, 为 01 。因此它不是一个回文数。
 *     </pre>
 * </p>
 *
 * @author jxiu
 * @date 2021/2/5
 */
public class No9 {

    public static void main(String[] args) {
        System.out.println("121 isPalindrome " + isPalindrome(9876789));
        System.out.println("9876789 isPalindrome " + isPalindrome(9876789));
        System.out.println("1221 isPalindrome " + isPalindrome(1221));
        System.out.printf("%d isPalindrome %b \n", -121, isPalindrome(-121));
        System.out.format("%d isPalindrome %b \n", 10, isPalindrome(10));
        System.out.format("%d isPalindrome %b \n", 12345, isPalindrome(12345));
        System.out.format("%d isPalindrome %b \n", 1, isPalindrome(1));
        System.out.format("%d isPalindrome %b \n", 0, isPalindrome(0));
    }

    public static boolean isPalindrome(int x) {
        if(x < 0 || (x%10 == 0 && x != 0)){
            return false;
        }
        // 反转
        int rev = 0;
        while(x > rev){
            rev = rev*10 + x%10;
            x = x/10;
        }

        return x == rev || x == rev/10;
    }

    public static boolean isPalindrome1(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10) {
            return true;
        }
        String xs = String.valueOf(x);
        char[] xcs = xs.toCharArray();
        for (int i = 0; i < xcs.length / 2; i++) {
            if (xcs[i] != xcs[xcs.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }
}
