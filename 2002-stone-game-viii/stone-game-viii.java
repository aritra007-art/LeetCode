class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        int[] preSum = new int[n];
        preSum[0] = stones[0];
        
       
        for (int i = 1; i < n; ++i) {
            preSum[i] = preSum[i - 1] + stones[i];
        }
        
      
        int res = preSum[n - 1];
        for (int i = n - 2; i > 0; --i) {
            res = Math.max(res, preSum[i] - res);
        }
        
        return res;
    }
}
