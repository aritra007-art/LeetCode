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
        // Base case: We need at least 3 nodes to have a critical point
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        int minDistance = Integer.MAX_VALUE;
        int firstCritical = -1;
        int prevCritical = -1;
        
        ListNode prev = head;
        ListNode curr = head.next;
        int index = 1; // 0-indexed positioning tracking

        while (curr.next != null) {
            ListNode next = curr.next;
            
            // Check if current node is a local maxima or local minima
            boolean isMaxima = curr.val > prev.val && curr.val > next.val;
            boolean isMinima = curr.val < prev.val && curr.val < next.val;

            if (isMaxima || isMinima) {
                // If it's the very first critical point we've encountered
                if (firstCritical == -1) {
                    firstCritical = index;
                } else {
                    // Update minDistance with the gap between consecutive critical points
                    minDistance = Math.min(minDistance, index - prevCritical);
                }
                // Update the trailing critical pointer to the current index
                prevCritical = index;
            }
            
            // Move pointers forward
            prev = curr;
            curr = next;
            index++;
        }

        // If less than 2 critical points were found, minDistance won't be updated
        if (minDistance == Integer.MAX_VALUE) {
            return new int[]{-1, -1};
        }

        int maxDistance = prevCritical - firstCritical;
        return new int[]{minDistance, maxDistance};
    }
}
