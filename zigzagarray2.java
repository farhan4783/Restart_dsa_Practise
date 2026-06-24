class Solution {
    private static final int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        int size = 2 * m;
        
        long[] V = new long[size];
        for (int y = 0; y < m; y++) {
            V[2 * y] = m - 1 - y;
            V[2 * y + 1] = y;
        }

        long[][] T = new long[size][size];
        for (int y = 0; y < m; y++) {
            for (int x = 0; x < m; x++) {
                if (x > y) {
                    T[2 * y][2 * x + 1] = 1;
                }
                if (x < y) {
                    T[2 * y + 1][2 * x] = 1;
                }
            }
        }

        T = power(T, n - 2);

        long[] result = new long[size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i] = (result[i] + T[i][j] * V[j]) % MOD;
            }
        }

        long total = 0;
        for (int i = 0; i < size; i++) {
            total = (total + result[i]) % MOD;
        }

        return (int) total;
    }

    private long[][] power(long[][] base, int exp) {
        int resSize = base.length;
        long[][] res = new long[resSize][resSize];
        for (int i = 0; i < resSize; i++) {
            res[i][i] = 1;
        }
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = multiply(res, base);
            }
            base = multiply(base, base);
            exp >>= 1;
        }
        return res;
    }

    private long[][] multiply(long[][] A, long[][] B) {
        int n = A.length;
        long[][] C = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (A[i][k] == 0) continue;
                for (int j = 0; j < n; j++) {
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }
}
