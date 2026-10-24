class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int arriveAliceDay = convertToDays(arriveAlice);
        int leaveAliceDay = convertToDays(leaveAlice);
        int arriveBobDay = convertToDays(arriveBob);
        int leaveBobDay = convertToDays(leaveBob);
        
        int startOverlap = Math.max(arriveAliceDay, arriveBobDay);
        int endOverlap = Math.min(leaveAliceDay, leaveBobDay);
        
        if (startOverlap <= endOverlap) {
            return endOverlap - startOverlap + 1;
        }
        return 0;
    }
    
    private int convertToDays(String date) {
        int[] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));
        int totalDays = day;
        for (int i = 0; i < month - 1; i++) {
            totalDays += daysInMonths[i];
        }
        return totalDays;
    }
}
