class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long c1 = cost1;
        long c2 = cost2;
        long cb = costBoth;
        long n1 = need1;
        long n2 = need2;

        // Option 1: Buy everything separately
        long allSeparate = (n1 * c1) + (n2 * c2);

        // Option 2: Use the combo item for the overlapping amount, then buy the rest separately
        long common = Math.min(n1, n2);
        long rem1 = n1 - common;
        long rem2 = n2 - common;
        long comboPlusRemaining = (common * cb) + (rem1 * c1) + (rem2 * c2);

        // Option 3: Buy only combo items to cover the larger requirement (even if it over-satisfies the smaller one)
        long allCombo = Math.max(n1, n2) * cb;

        return Math.min(allSeparate, Math.min(comboPlusRemaining, allCombo));
    }
}
