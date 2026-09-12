
class Solution {
    // A structure to store interval metadata
    private static class Interval {
        int left, right, weight, originalIndex;

        Interval(int left, int right, int weight, int originalIndex) {
            this.left = left;
            this.right = right;
            this.weight = weight;
            this.originalIndex = originalIndex;
        }
    }

    // A structure to capture the DP result tracking max weight and index path
    private static class Result {
        long weight;
        List<Integer> chosenIndices;

        Result(long weight, List<Integer> chosenIndices) {
            this.weight = weight;
            this.chosenIndices = chosenIndices;
        }
    }

    private Result[][] memo;

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        List<Interval> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> interval = intervals.get(i);
            list.add(new Interval(interval.get(0), interval.get(1), interval.get(2), i));
        }

        // Sort primarily by left boundary
        list.sort(Comparator.comparingInt(a -> a.left));

        memo = new Result[n][5];
        Result optimalResult = solve(list, 0, 4);

        // Sort the chosen indices before returning to align with standard order configurations
        List<Integer> finalIndices = new ArrayList<>(optimalResult.chosenIndices);
        Collections.sort(finalIndices);

        int[] ans = new int[finalIndices.size()];
        for (int i = 0; i < finalIndices.size(); i++) {
            ans[i] = finalIndices.get(i);
        }
        return ans;
    }

    private Result solve(List<Interval> list, int index, int quota) {
        if (index >= list.size() || quota == 0) {
            return new Result(0, new ArrayList<>());
        }

        if (memo[index][quota] != null) {
            return memo[index][quota];
        }

        // Scenario 1: Skip the current interval
        Result skipResult = solve(list, index + 1, quota);

        // Scenario 2: Take the current interval
        Interval current = list.get(index);
        int nextIndex = findNext(list, index + 1, current.right);
        Result takeNext = solve(list, nextIndex, quota - 1);

        long takeWeight = current.weight + takeNext.weight;
        List<Integer> takeIndices = new ArrayList<>();
        takeIndices.add(current.originalIndex);
        takeIndices.addAll(takeNext.chosenIndices);

        Result takeResult = new Result(takeWeight, takeIndices);

        // Decide between 'skip' and 'take' based on weights and lexicographical order
        memo[index][quota] = getBetterResult(skipResult, takeResult);
        return memo[index][quota];
    }

    // Binary search to find the first interval starting after the current interval's right end
    private int findNext(List<Interval> list, int start, int targetRight) {
        int low = start, high = list.size() - 1;
        int ans = list.size();
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid).left > targetRight) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private Result getBetterResult(Result r1, Result r2) {
        if (r1.weight != r2.weight) {
            return r1.weight > r2.weight ? r1 : r2;
        }

        // Deep tiebreaker comparison using sorted copies of paths
        List<Integer> path1 = new ArrayList<>(r1.chosenIndices);
        List<Integer> path2 = new ArrayList<>(r2.chosenIndices);
        Collections.sort(path1);
        Collections.sort(path2);

        int len = Math.min(path1.size(), path2.size());
        for (int i = 0; i < len; i++) {
            if (!path1.get(i).equals(path2.get(i))) {
                return path1.get(i) < path2.get(i) ? r1 : r2;
            }
        }
        return path1.size() <= path2.size() ? r1 : r2;
    }
}
