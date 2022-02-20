package lc101.string;

/**
 * 字符串匹配
 * 判断一个字符串是不是另一个字符串的子字符串，并返回其位置。
 */
public class Kmp {



    /**
     * KMP算法搜索
     * @param str 给定字符串
     * @param match 需要匹配的主字符串
     * @return 子串开始位置
     */
    public static int kmpSearch(String str, String match){
        int idx = -1;
        int[] next = getNext(match);
        return idx;
    }

    private static int[] getNext(String match){
        int[] next = new int[match.length()];

        int sameCount = 0;
        for (int i = 1; i < match.length(); i++) {
            // 已经有匹配的之后遇到不匹配的时候重制匹配数

            while(sameCount > 0 && match.charAt(i) != match.charAt(sameCount)){
                sameCount = next[sameCount-1];
            }
            // 从第一个开始匹配匹配上之后就 sameCount+1
            if (match.charAt(i) == match.charAt(sameCount)){
                sameCount++;
            }
            next[i] = sameCount;

        }
        return next;
    };

    /**
     *  暴力字符串搜索
     * @param str 给定字符串
     * @param match 需要匹配的主字符串
     * @return 子串开始位置
     */
    public static int stringSearch(String str, String match){
        int idx = -1;
        for (int i = 0; i < str.length(); i++) {
            int matchCount = 0;
            for (; matchCount < match.length(); matchCount++) {
                if (str.length()-1-i < match.length()) return idx;
                if (str.charAt(i+matchCount) != match.charAt(matchCount)){
                    break;
                }
            }
            if (matchCount == match.length()){
                return i;
            }
        }
        return idx;
    }






    public static class KMPMatch {

        public static int kmpSearch(String str, String match) {
            int[] next = getNext(match);

            for (int i = 0, j = 0; i < str.length(); i++) {
                // 算法核心点
                while (j > 0 && str.charAt(i) != match.charAt(j)) {
                    // 根据部分匹配表，更新 j
                    j = next[j - 1];
                }

                if (str.charAt(i) == match.charAt(j)) {
                    j++;
                }
                // 判断是否找到了
                if (j == match.length()) {
                    return i - (j - 1);
                }
            }
            return -1;
        }

        public static int[] getNext(String match) {
            int[] next = new int[match.length()];

            // 第一个字符的值为 0
            next[0] = 0;

            for (int i = 1, j = 0; i < match.length(); i++) {

                // 核心，比较直到相等
                while (j > 0 && match.charAt(i) != match.charAt(j)) {
                    // 更新 j
                    j = next[j - 1];
                }

                // 相等
                if (match.charAt(i) == match.charAt(j)) {
                    j++;
                }
                next[i] = j;
            }
            return next;
        }

        public static void main(String[] args) {
            String str = "CBC DCABCABABCABD BBCCA";
            String match = "ABCABDABC";
            int index = kmpSearch(str, match);
            System.out.printf("%s 在 %s 中的位置为 %d", match, str, index);
        }

    }

    
    
}
