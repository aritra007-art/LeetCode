class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] ans = new int[n];
        int depth = 0;

        for (int i = 0; i < n; i++) {
            char c = seq.charAt(i);
            if (c == '(') {
                // Assign to group 0 or 1 based on current depth parity, then increment depth
                ans[i] = depth & 1;
                depth++;
            } else {
                // Decrement depth first, then assign based on updated depth parity
                depth--;
                ans[i] = depth & 1;
            }
        }
        
        return ans;
    }
}
