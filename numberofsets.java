class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1_000_000_007;
        long[][] dp = new long[n][k + 1];
        long[][] prefixSum = new long[n][k + 1];

        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
            prefixSum[i][0] = i + 1;
        }

        for (int j = 1; j <= k; j++) {
            for (int i = 1; i < n; i++) {
                dp[i][j] = (dp[i - 1][j] + prefixSum[i - 1][j - 1]) % MOD;
                prefixSum[i][j] = (prefixSum[i - 1][j] + dp[i][j]) % MOD;
            }
        }

        return (int) dp[n - 1][k];
    }
}
