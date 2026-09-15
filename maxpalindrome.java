class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int[] dp = new int[n + 1];
        
        for (int i = 0; i < n; i++) {
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
            
            expand(s, i, i, k, dp);
            expand(s, i, i + 1, k, dp);
        }
        
        return dp[n];
    }
    
    private void expand(String s, int l, int r, int k, int[] dp) {
        int n = s.length();
        while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
            if (r - l + 1 >= k) {
                dp[r + 1] = Math.max(dp[r + 1], dp[l] + 1);
                break;
            }
            l--;
            r++;
        }
    }
}
