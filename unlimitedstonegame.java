class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        
     
        long sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        
       
        long maxDiff = sum;
        
        
        for (int i = n - 1; i > 1; i--) {
            sum -= stones[i];
            maxDiff = Math.max(maxDiff, sum - maxDiff);
        }
        
        return (int) maxDiff;
    }
}
