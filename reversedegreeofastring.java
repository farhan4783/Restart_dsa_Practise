class Solution {
    public int reverseDegree(String s) {
        int totalReverseDegree = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            
            int reverseAlphabetRank = 'z' - ch + 1;
            
           
            int stringPosition = i + 1;
            
            totalReverseDegree += reverseAlphabetRank * stringPosition;
        }
        
        return totalReverseDegree;
    }
}
