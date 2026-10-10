class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long totalK = (long) k1 + k2;
        int maxD = 0;
        int[] diff = new int[n];
        long totalDiff = 0;
        
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            maxD = Math.max(maxD, diff[i]);
            totalDiff += diff[i];
        }
        
        if (totalDiff <= totalK) {
            return 0;
        }
        
        int[] count = new int[maxD + 1];
        for (int d : diff) {
            count[d]++;
        }
        
        for (int d = maxD; d > 0 && totalK > 0; d--) {
            if (count[d] == 0) continue;
            long take = Math.min(totalK, count[d]);
            count[d] -= take;
            count[d - 1] += take;
            totalK -= take;
        }
        
        long ans = 0;
        for (int d = 1; d <= maxD; d++) {
            if (count[d] > 0) {
                ans += (long) count[d] * d * d;
            }
        }
        return ans;
    }
}
