public class Solution {
    public int zigZagArrays(int n, int l, int r) {
        int M = r - l + 1;
        long MOD = 1000000007L;

        
        long[] up = new long[M];
        long[] down = new long[M];

       
        for (int j = 0; j < M; j++) {
            up[j] = j;
            down[j] = M - 1 - j;
        }

       
        for (int i = 3; i <= n; i++) {
            long[] nextUp = new long[M];
            long[] nextDown = new long[M];

          
            long[] prefDown = new long[M + 1];
            long[] suffUp = new long[M + 1];

            for (int j = 0; j < M; j++) {
                prefDown[j + 1] = (prefDown[j] + down[j]) % MOD;
            }
            for (int j = M - 1; j >= 0; j--) {
                suffUp[j] = (suffUp[j + 1] + up[j]) % MOD;
            }

           
            for (int j = 0; j < M; j++) {
               
                nextUp[j] = prefDown[j];
                
               
                nextDown[j] = suffUp[j + 1];
            }

            up = nextUp;
            down = nextDown;
        }

        
        long totalWays = 0;
        for (int j = 0; j < M; j++) {
            totalWays = (totalWays + up[j] + down[j]) % MOD;
        }

        return (int) totalWays;
    }
}
