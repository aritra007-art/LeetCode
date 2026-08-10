class Solution {
    public int findMaxK(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        int maxK = -1;

        for (int num : nums) {
            seen.add(num);
        }

        for (int num : nums) {
            if (seen.contains(-num)) {
                maxK = Math.max(maxK, Math.abs(num));
            }
        }

        return maxK;
    }
}
