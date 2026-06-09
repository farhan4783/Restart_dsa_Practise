class Solution {
    public long maxTotalValue(int[] nums, int k) {
        long minVal = Integer.MAX_VALUE;
        long maxVal = Integer.MIN_VALUE;
        
        for (int num : nums) {
            if (num < minVal) minVal = num;
            if (num > maxVal) maxVal = num;
        }
        
        return (maxVal - minVal) * k;
    }
}
