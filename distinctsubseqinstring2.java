class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        long[] last = new long[26];
        
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            long currentCount = 1;
            for (int i = 0; i < 26; i++) {
                currentCount = (currentCount + last[i]) % MOD;
            }
            last[idx] = currentCount;
        }
        
        long result = 0;
        for (int i = 0; i < 26; i++) {
            result = (result + last[i]) % MOD;
        }
        
        return (int) result;
    }
}
