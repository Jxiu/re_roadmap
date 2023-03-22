package base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

public class FloatTest {

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(0.75F)));// 0.11 1.1 X 2(-1)
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(8.25F)));// 1000.01 1.00001 X 2(3)
        //0 01111110 10000000000000000000000 1.1 X 2()
        //0 10000010 00001000000000000000000
        //0    1   1    1  1  1 1 0
        //1    0   0    0  0  0 1 0
        //128  64  32   16 8  4 2 1

        //0 0111111010 000000000000000000000
        //0 1000001000 001000000000000000000

        TreeMap<Integer, String> tm = new TreeMap<Integer, String>();
        tm.put(1,"1");
        tm.put(2,"2");
        System.out.println(tm.firstEntry().getValue());
        new ArrayList<>().toArray(new String[0]);
    }

    class Solution {
        public int countMaxOrSubsets(int[] nums) {
            // 遍历所有可能性
            int max = 0, count = 0;
            for(int i = 0;i < (1 << nums.length); i ++){
                int cur = 0;
                for(int j = 0; j < nums.length; j++){
                    // 代表这种组合
                    if(((i >> j) & 1) ==1){
                        cur |= nums[j];
                    }

                }
                if(cur == max){
                    count++;
                }else if(max > cur){
                    max = cur;
                    // 重置次数
                    count = 1;
                }
            }
            return count;
        }
    }

    class Solution1 {
        public int countMaxOrSubsets(int[] nums) {
            int maxOr = 0, cnt = 0;
            for (int i = 0; i < 1 << nums.length; i++) {
                int orVal = 0;
                for (int j = 0; j < nums.length; j++) {
                    if (((i >> j) & 1) == 1) {
                        orVal |= nums[j];
                    }
                }
                if (orVal > maxOr) {
                    maxOr = orVal;
                    cnt = 1;
                } else if (orVal == maxOr) {
                    cnt++;
                }
            }
            return cnt;
        }
    }


    class AllOne {

        private Map<String, Integer> data;
        private TreeMap<Integer, Set<String>> cntMap;

        public AllOne() {
            data = new HashMap();
            cntMap = new TreeMap<>();
        }

        public void inc(String key) {
            Integer cnt = data.getOrDefault(key, 0);
            int cur = cnt+1;
            data.put(key, cur);
            Set<String> oset = cntMap.getOrDefault(cnt, new HashSet<>());
            oset.remove(key);
            if (oset.size() == 0){
                cntMap.remove(cnt);
            }
            Set<String> cset = cntMap.getOrDefault(cur, new HashSet<>());
            cset.add(key);
        }

        public void dec(String key) {
            Integer cnt = data.getOrDefault(key, 0);
            Integer cur = cnt -1;
            Set<String> oset = cntMap.getOrDefault(cnt, new HashSet<>());
            oset.remove(key);
            if (oset.size() == 0){
                cntMap.remove(cnt);
            }
            if (cur < 1){
                data.remove(key);
            }else {
                data.put(key,cur);
                Set<String> cset = cntMap.getOrDefault(cnt, new HashSet<>());
                cset.add(key);
            }
        }

        public String getMaxKey() {
            Map.Entry<Integer, Set<String>> key = cntMap.lastEntry();
            if (key == null){
                return null;
            }
            return key.getValue().stream().findFirst().get();
        }

        public String getMinKey() {
            Map.Entry<Integer, Set<String>> key = cntMap.firstEntry();
            if (key == null){
                return null;
            }
            return key.getValue().stream().findFirst().get();
        }
    }
  class AllOne1 {

        private Map<String, Integer> data;
//        private Integer maxCnt = 0;
//        private String maxKey = "";
//        private Integer minCnt = 0;
//        private String minKey = "";
        public AllOne1() {
            data = new HashMap();
        }

        public void inc(String key) {
            Integer cnt = data.getOrDefault(key, 0);
            int cur = cnt+1;
            data.put(key, cur);
//            if (cur > maxCnt){
//                maxCnt = cur;
//                maxKey = key;
//            }
//            if (cur)
        }

        public void dec(String key) {
            Integer cnt = data.getOrDefault(key, 0);
            Integer cur = cnt -1;
            if (cur < 1){
                data.remove(key);
            }else {
                data.put(key,cur);
            }
        }

        public String getMaxKey() {
            return data.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).map(Map.Entry::getKey).orElse("");
        }

        public String getMinKey() {
            return data.entrySet().stream().min(Comparator.comparing(Map.Entry::getValue)).map(Map.Entry::getKey).orElse("");
        }
    }


}
