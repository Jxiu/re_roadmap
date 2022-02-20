package lc101.greedy;

import java.util.Arrays;

/**
 * 有一群孩子和一堆饼干，每个孩子有一个饥饿度，每个饼干都有一个大小。每个孩子只能吃 一个饼干，且只有饼干的大小不小于孩子的饥饿度时，这个孩子才能吃饱。求解最多有多少孩子 可以吃饱。
 */
public class LN455 {

    /**
     *  返回最多多少孩子能吃饱
     * @param children 孩子的饥饿度
     * @param cookies 饼干大小
     * @return
     */
    public static int findContentChildren(int[] children, int[] cookies){
        Arrays.sort(children);
        Arrays.sort(cookies);
        int j = 0, num = 0;
        for (int i = 0; i < children.length; i++) {
            for (; j < cookies.length; j++) {
                if (children[i] <= cookies[j]){
                    num++;
                    break;
                }
            }
        }
        return num;
    }

    public static int findContentChildren1(int[] children, int[] cookies){
        Arrays.sort(children);
        Arrays.sort(cookies);
        int child = 0,cookie = 0;
        while (child < children.length && cookie < cookies.length){
            if (children[child] < cookies[cookie]) {
                // 如果当前饼干满足饥饿值最小的小孩，则匹配下一个饥饿值的小孩
                child++;
            }
            cookie++;
        }
        return child;
    }
}
