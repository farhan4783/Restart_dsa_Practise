class Solution {
    public int minElement(int[] nums) {
        int ans = 100;
        for (int x : nums) {
            int y = 0;
            int t = x;
            while (t > 0) {
                y += t % 10;
                t /= 10;
            }
            ans = Math.min(ans, y);
        }
        return ans;
    }
}
