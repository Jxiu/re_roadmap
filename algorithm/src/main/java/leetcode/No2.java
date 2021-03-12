package leetcode;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 两数相加
 * @author jxiu
 * @date 2021/2/20
 */
public class No2 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode().reverseNumberList("342");
        System.out.println(l1);
        ListNode l2 = new ListNode().reverseNumberList("465");
        System.out.println(l2);
        ListNode re = addTwoNumbers(l1,l2);
        System.out.println(re);
//
//        l1 = new ListNode().reverseNumberList("9999999");
//        System.out.println(l1);
//        l2 = new ListNode().reverseNumberList("9999");
//        System.out.println(l2);
//        re = addTwoNumbers(l1,l2);
//        System.out.println(re);

        l1 = new ListNode().reverseNumberList("1");
        System.out.println(l1);
        l2 = new ListNode().reverseNumberList("99");
        System.out.println(l2);
        re = addTwoNumbers(l1,l2);
        System.out.println(re);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }
        ListNode l1Curr = l1, l2Curr = l2,  curr = null, prev = null;
        // 溢出
        int over = 0;
        while (l1Curr != null || l2Curr != null || over != 0) {
            int v1 = 0, v2 = 0;
            if (l1Curr != null) {
                curr = l1Curr;
                v1 = l1Curr.val;
            }
            if (l2Curr != null) {
                if (l1Curr == null){
                    curr = l2Curr;
                }
                v2 = l2Curr.val;
            }
            if (curr == null){
                curr = new ListNode();
            }
            int result = v1 + v2 + over;
            if (result > 9) {
                over = 1;
                result = result - 10;
            } else {
                over = 0;
            }
            curr.val = result;
            if (l1Curr == null) {
                prev.next = curr;
            }

            if (l1Curr != null){
                l1Curr = l1Curr.next;
            }
            if (l2Curr != null){
                l2Curr = l2Curr.next;
            }
            prev = curr;
            curr = null;
        }
        return l1;
    }

    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }
        ListNode l1Curr = l1, l2Curr = l2, head = null, curr = null;
        // 溢出
        int over = 0;
        while (l1Curr != null || l2Curr != null || over != 0) {
            int v1 = 0, v2 = 0;
            if (l1Curr != null) {
                v1 = l1Curr.val;
                l1Curr = l1Curr.next;
            }
            if (l2Curr != null) {
                v2 = l2Curr.val;
                l2Curr = l2Curr.next;
            }
            int result = v1 + v2 + over;
            if (result > 9) {
                over = 1;
                result = result - 10;
            } else {
                over = 0;
            }
            ListNode resultNode = new ListNode(result);
            if (head == null) {
                head = resultNode;
            }
            if (curr != null) {
                curr.next = resultNode;
            }
            curr = resultNode;
        }
        return head;
    }

    static class ListNode {
        private static final ThreadLocalRandom random = ThreadLocalRandom.current();
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

        public ListNode numberList(int length) {
            if (length <= 0) return null;
            ListNode head = null, current, pre = null;
            for (int i = 0; i < length; i++) {
                int val = random.nextInt(10);
                if (i == length - 1){
                    while (val == 0){
                        val = random.nextInt(10);
                    }
                }
                current = new ListNode(val);
                if (null == head) {
                    head = current;
                } else {
                    pre.next = current;
                }
                pre = current;
            }
            return head;
        }

        public ListNode reverseNumberList(String numberString) {
            ListNode head = null, current, pre = null;
            for (int i = numberString.length()-1; i >= 0; i--) {
                char c = numberString.charAt(i);
                current = new ListNode(c - 48);
                if (null == head) {
                    head = current;
                } else {
                    pre.next = current;
                }
                pre = current;
            }
            return head;
        }

        public ListNode randomList(int length, int min, int range) {
            if (length <= 0) return null;
            ListNode head = null, current, pre = null;
            for (int i = 0; i < length; i++) {
                int val = min + random.nextInt(range);
                current = new ListNode(val);
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
