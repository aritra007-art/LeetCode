class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        
        // Step 1: Pair each element with its original index
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums[i];
            pairs[i][1] = i;
        }
        
        // Step 2: Sort the pairs based on their values
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));
        
        int[] result = new int[n];
        
        // Step 3: Use a two-pointer approach to find and process connected groups
        int i = 0;
        while (i < n) {
            int j = i + 1;
            // Extend the group as long as adjacent sorted elements differ by at most 'limit'
            while (j < n && pairs[j][0] - pairs[j - 1][0] <= limit) {
                j++;
            }
            
            // Collect and sort the original indices for the current group
            int[] indices = new int[j - i];
            for (int k = i; k < j; k++) {
                indices[k - i] = pairs[k][1];
            }
            Arrays.sort(indices);
            
            // Assign the sorted values to the sorted positions
            for (int k = i; k < j; k++) {
                result[indices[k - i]] = pairs[k][0];
            }
            
            // Move onto the next group
            i = j;
        }
        
        return result;
    }
}
