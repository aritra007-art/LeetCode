class Solution {
    private final int[] daysInMonths = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int arriveA = dateToDayOfYear(arriveAlice);
        int leaveA = dateToDayOfYear(leaveAlice);
        int arriveB = dateToDayOfYear(arriveBob);
        int leaveB = dateToDayOfYear(leaveBob);

        int startOverlap = Math.max(arriveA, arriveB);
        int endOverlap = Math.min(leaveA, leaveB);

        // Calculate overlap length, clamp at 0 if no intersection exists
        return Math.max(0, endOverlap - startOverlap + 1);
    }

    private int dateToDayOfYear(String date) {
        // Parse month and day from "MM-DD"
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));

        // Accumulate days from all preceding months
        int dayCount = 0;
        for (int m = 1; m < month; m++) {
            dayCount += daysInMonths[m];
        }

        return dayCount + day;
    }
}