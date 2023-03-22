package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

public class No432 {

//    static class AllOne{
//        private Map<String, Integer> data;
//        private ConcurrentHashMap<String, Integer> a;
//        private TreeMap<Integer, Set<String>> cntMap;
//
//        public AllOne() {
//            ThreadPoolExecutor executor = new ThreadPoolExecutor();
//            data = new HashMap();
//            cntMap = new TreeMap<>();
//        }
//
//        public void inc(String key) {
//            Integer cnt = data.getOrDefault(key, 0);
//            int cur = cnt+1;
//            data.put(key, cur);
//            Set<String> oset = cntMap.getOrDefault(cnt, new HashSet<>());
//            oset.remove(key);
//            if (oset.size() == 0){
//                cntMap.remove(cnt);
//            }
//            Set<String> cset = cntMap.getOrDefault(cur, new HashSet<>());
//            cset.add(key);
//        }
//
//        public void dec(String key) {
//            Integer cnt = data.getOrDefault(key, 0);
//            Integer cur = cnt -1;
//            Set<String> oset = cntMap.getOrDefault(cnt, new HashSet<>());
//            oset.remove(key);
//            if (oset.size() == 0){
//                cntMap.remove(cnt);
//            }
//            if (cur < 1){
//                data.remove(key);
//            }else {
//                data.put(key,cur);
//                Set<String> cset = cntMap.getOrDefault(cnt, new HashSet<>());
//                cset.add(key);
//            }
//        }
//
//        public String getMaxKey() {
//            Map.Entry<Integer, Set<String>> key = cntMap.lastEntry();
//            if (key == null){
//                return null;
//            }
//            return key.getValue().stream().findFirst().get();
//        }
//
//        public String getMinKey() {
//            Map.Entry<Integer, Set<String>> key = cntMap.firstEntry();
//            if (key == null){
//                return null;
//            }
//            return key.getValue().stream().findFirst().get();
//        }
//    }
}
