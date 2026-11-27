
class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int[] dp = new int[budget + 1];
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];
            minPrice = Math.min(minPrice, price);

            // Count total items gained (1 purchased + all free divisible items)
            int itemsGained = 0;
            for (int[] other : items) {
                if (other[0] % factor == 0) {
                    itemsGained++;
                }
            }

            // 0/1 Knapsack for the first copy of each item
            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(dp[b], dp[b - price] + itemsGained);
            }
        }

        // Spend any leftover budget greedily on the cheapest item
        int maxItems = 0;
        for (int spent = 0; spent <= budget; spent++) {
            int remaining = budget - spent;
            maxItems = Math.max(maxItems, dp[spent] + (remaining / minPrice));
        }

        return maxItems;
    }
}
