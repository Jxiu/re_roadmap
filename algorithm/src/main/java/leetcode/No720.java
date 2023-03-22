package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.concurrent.Executors;

public class No720 {

   static class Solution {
        public String longestWord(String[] words) {
            Arrays.stream(words).sorted(Comparator.comparing(String::length).thenComparing(String::compareTo));
            String res = "";
            int maxLen = 0;
            for (int i = 0; i < words.length; i++) {
                for (int j = 0; j < words.length; j++) {
                    if (words[j].startsWith(words[i])){
                        if (words[j].length() > maxLen){
                            maxLen = words[j].length();
                            res = words[j];
                        }
                    }else {
                        break;
                    }
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put("a","");
        Executors.newWorkStealingPool();
    }
}
