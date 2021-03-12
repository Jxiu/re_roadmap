package leetcode;

import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * 反转一个单链表
 *
 * <p>
 * 实例:
 * <pre>
 *         输入: 1->2->3->4->5->NULL
 *         输出: 5->4->3->2->1->NULL
 *     </pre>
 * </p>
 *
 * @author jxiu
 * @date 2021/1/29
 */
public class No206_92 {
    HashMap hashMap;
    public static final Pattern p1 = Pattern.compile("(?<=^\\d{4})\\d{10}(?=\\d{4}$)");

    public static void main(String[] args) {
//        Matcher matcher = p1.matcher("411323199512019110");
//        if (matcher.find()){
//            System.out.println(matcher.group());
//        }
//        System.out.println(matcher.replaceAll("**********"));
        Solution solution = new Solution();

//        ListNode head = new ListNode().serialList(4);
//        System.out.println("serialList = " + head);
//        head = solution.reverseList(head);
//        System.out.println("reverseList = " + head);
//        head = solution.recursion(head);
//        System.out.println("recursion = " + head);
        ListNode head = new ListNode().serialList(5);
        System.out.println("reverseBetween = " + head);
        head = solution.reverseBetween(head,3,5);
        System.out.println("reverseBetween = " + head);


    }

    static class Solution {
        /**
         * 92. 反转链表 II
         * <p>
         * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
         * <pre>
         *     输入: 1->2->3->4->5->NULL, m = 2, n = 4
         *     输出: 1->4->3->2->5->NULL
         * </pre>
         * </p>
         * @param head
         * @param m 1 ≤ m ≤ n ≤ 链表长度。
         * @param n
         * @return
         */
        public ListNode reverseBetween(ListNode head, int m, int n) {
            ListNode curr = head, pre = null, mpre= null, mcurr = null,next;
            int idx = 1;
            while (curr != null){
                next = curr.next;
                if (m <= idx && n >= idx){
                    if (m == idx){
                        mpre = pre;
                        mcurr = curr;
                        curr.next = null;
                    }else {
                        curr.next = pre;
                    }
                    if(n == idx){
                        if (mpre != null){
                            mpre.next = curr;
                        }else {
                            head = curr;
                        }
                        mcurr.next = next;
                        break;
                    }
                }
                pre = curr;
                curr = next;
                idx++;
            }
            return head;
        }

        public ListNode reverseList(ListNode head) {
            if (head == null) {
                return null;
            }
            ListNode pre = null,curr = head, next = null;
            while (curr != null) {
                next = curr.next;
                curr.next = pre;
                pre = curr;
                curr = next;
            }
            return pre;
        }

        public ListNode recursion(ListNode head){
            if(head == null){
                return null;
            }
            ListNode last = recursion(head.next);
            if (head.next == null){
                // 最后一个节点直接返回
                return head;
            }
            // 将节点和next的关系反转
            head.next.next = head;
            // 防止形成环状
            head.next = null;
            return last;
        }


    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public ListNode serialList(int length) {
            if (length <= 0) return null;
            ListNode head = null, current, pre = null;
            for (int i = 0; i < length; i++) {
                current = new ListNode(i);
                if (null == head) {
                    head = current;
                } else {
                    pre.next = current;
                }
                pre = current;

            }
            return head;
        }


        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            ListNode i = this;
            do {
                sb.append(i.val);
                i = i.next;
                if (i != null) {
                    sb.append(", ");
                }
            } while (i != null);
            return sb.toString();
        }
    }


}

