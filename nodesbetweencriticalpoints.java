/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        ListNode prev = head;
        ListNode curr = head.next;
        int index = 1;

        int firstCritical = -1;
        int lastCritical = -1;
        int prevCritical = -1;
        int minDistance = Integer.MAX_VALUE;

        while (curr.next != null) {
            boolean isLocalMin = curr.val < prev.val && curr.val < curr.next.val;
            boolean isLocalMax = curr.val > prev.val && curr.val > curr.next.val;

            if (isLocalMin || isLocalMax) {
                if (firstCritical == -1) {
                    firstCritical = index;
                } else {
                    minDistance = Math.min(minDistance, index - prevCritical);
                }
                prevCritical = index;
                lastCritical = index;
            }

            prev = curr;
            curr = curr.next;
            index++;
        }

        if (firstCritical == -1 || firstCritical == lastCritical) {
            return new int[]{-1, -1};
        }

        int maxDistance = lastCritical - firstCritical;

        return new int[]{minDistance, maxDistance};
    }
}
