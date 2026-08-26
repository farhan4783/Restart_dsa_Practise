class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        String result = "";
        
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = i; j < n; j++) {
                if (s.charAt(j) == '1') {
                    count++;
                }
                
                if (count == k) {
                    String sub = s.substring(i, j + 1);
                    if (result.isEmpty() || sub.length() < result.length() || (sub.length() == result.length() && sub.compareTo(result) < 0)) {
                        result = sub;
                    }
                    break;
                }
            }
        }
        
        return result;
    }
}
