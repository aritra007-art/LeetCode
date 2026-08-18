public class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;

       
        if (k == 1) {
            Map<Integer, Integer> counts = new HashMap<>();
            for (int num : nums) {
                counts.put(num, counts.getOrDefault(num, 0) + 1);
            }
            int maxVal = -1;
            for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
                if (entry.getValue() == 1) {
                    maxVal = Math.max(maxVal, entry.getKey());
                }
            }
            return maxVal;
        }

      
        if (k == n) {
            int maxVal = -1;
            for (int num : nums) {
                maxVal = Math.max(maxVal, num);
            }
            return maxVal;
        }

      
        int first = nums[0];
        int last = nums[n - 1];
        
        
        int firstCount = 0;
        int lastCount = 0;
        for (int num : nums) {
            if (num == first) firstCount++;
            if (num == last) lastCount++;
        }

        int result = -1;
        if (firstCount == 1) {
            result = Math.max(result, first);
        }
        if (lastCount == 1) {
            result = Math.max(result, last);
        }

        return result;
    }
}
