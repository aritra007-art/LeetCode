
public class Solution {
    public int minOperations(String s) {
        int n = s.length();
        
        // Step 1: Check if the string is already sorted
        boolean isSorted = true;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) < s.charAt(i - 1)) {
                isSorted = false;
                break;
            }
        }
        if (isSorted) return 0;
        
        // Step 2: An unsorted string of length 2 cannot be sorted under constraints
        if (n == 2) return -1;
        
        // Step 3: Find the positions of the global minimum and maximum characters
        int minIdx = 0;
        int maxIdx = 0;
        
        for (int i = 1; i < n; i++) {
            // Find leftmost global minimum
            if (s.charAt(i) < s.charAt(minIdx)) {
                minIdx = i;
            }
            // Find rightmost global maximum
            if (s.charAt(i) >= s.charAt(maxIdx)) {
                maxIdx = i;
            }
        }
        
        // Case 1: Minimum is at the start or Maximum is at the end -> 1 operation
        if (minIdx == 0 || maxIdx == n - 1) {
            return 1;
        }
        
        // Case 3: Worst case (Min is at the end and Max is at the start) -> 3 operations
        if (minIdx == n - 1 && maxIdx == 0) {
            return 3;
        }
        
        // Case 2: General scrambled case -> 2 operations
        return 2;
    }
}
