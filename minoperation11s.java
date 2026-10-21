
class Solution {
    public int minOperations(String s) {
        int n = s.length();
        boolean sorted = true;

        char minChar = s.charAt(0);
        char maxChar = s.charAt(0);

        for (int i = 1; i < n; i++) {
            char c = s.charAt(i);

            minChar = (char) Math.min(minChar, c);
            maxChar = (char) Math.max(maxChar, c);

            if (c < s.charAt(i - 1)) {
                sorted = false;
            }
        }

     
        if (sorted) {
            return 0;
        }

       
        if (n == 2) {
            return -1;
        }

       
        if (s.charAt(0) == minChar ||
            s.charAt(n - 1) == maxChar) {
            return 1;
        }

      
        for (int i = 1; i < n - 1; i++) {
            char c = s.charAt(i);

            if (c == minChar || c == maxChar) {
                return 2;
            }
        }

       
        return 3;
    }
}
