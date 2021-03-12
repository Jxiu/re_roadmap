package datastruct;

/**
 * @author jxiu
 * @date 2021/2/20
 */
public class SimpleLinkedList {

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
