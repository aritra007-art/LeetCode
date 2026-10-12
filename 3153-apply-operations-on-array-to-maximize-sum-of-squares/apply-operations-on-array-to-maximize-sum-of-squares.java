

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        final int MOD = 1_000_000_007;
        final int MAX_BIT = 32;
        int[] count = new int[MAX_BIT];

        // Step 1: Count the frequency of set bits at each position across all numbers
        for (int num : nums) {
            for (int i = 0; i < MAX_BIT; i++) {
                if (((num >> i) & 1) == 1) {
                    count[i]++;
                }
            }
        }

        long ans = 0;

        // Step 2: Greedily construct the top k largest numbers
        for (int step = 0; step < k; step++) {
            long currNum = 0;
            for (int i = MAX_BIT - 1; i >= 0; i--) {
                if (count[i] > 0) {
                    currNum |= (1L << i);
                    count[i]--;
                }
            }
            ans = (ans + (currNum * currNum) % MOD) % MOD;
        }

        return (int) ans;
    }
}
