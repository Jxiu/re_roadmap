package leetcode;

/**
 * @author jxiu
 * @date 2021/2/19
 */
public class No8 {

    public static void main(String[] args) {
        System.out.printf("%s myAtoi result is : %d \n", "42", myAtoi("42"));
        System.out.printf("%s myAtoi result is : %d \n", "   -42", myAtoi("   -42"));
        System.out.printf("%s myAtoi result is : %d \n", "4193 with words", myAtoi("4193 with words"));
        System.out.printf("%s myAtoi result is : %d \n", "words and 987", myAtoi("words and 987"));
        System.out.printf("%s myAtoi result is : %d \n", "-91283472332", myAtoi("-91283472332"));
        System.out.printf("%s myAtoi result is : %d \n", "3.14159", myAtoi("3.14159"));
    }

    public static int myAtoi(String s) {
        int result = 0;
        boolean begin = false;
        char flag = 'A';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!begin) {
                if (c == ' ') {
                    continue;
                } else if (c == '+' || c == '-' || (c >= 48 && c <= 57)) {
                    begin = true;
                } else {
                    return result;
                }
            }

            if (c == '-') {
                if (flag == 'A') {
                    flag = '-';
                    continue;
                }
            }
            if (c == '+') {
                if (flag == 'A') {
                    flag = '+';
                    continue;
                }
            }

            if (c >= 48 && c <= 57) {
                if (flag == 'A') {
                    flag = '+';
                }
                int temp = result;
                if (flag == '+') {
                    result = result * 10 + (c - 48);
                    if (temp != result / 10) {
                        return Integer.MAX_VALUE;
                    }
                }
                if (flag == '-') {
                    result = result * 10 - (c - 48);
                    if (temp != result / 10) { // 数字溢出
                        return Integer.MIN_VALUE;
                    }
                }
                continue;
            }
            break;
        }
        return result;
    }
}
