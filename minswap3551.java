import java.util.Arrays;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        
      
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        
        
        Arrays.sort(indices, (a, b) -> {
            int sumA = getDigitSum(nums[a]);
            int sumB = getDigitSum(nums[b]);
            if (sumA != sumB) {
                return Integer.compare(sumA, sumB);
            }
            return Integer.compare(nums[a], nums[b]);
        });
        
        
        int[] targetPos = new int[n];
        for (int i = 0; i < n; i++) {
            targetPos[indices[i]] = i;
        }
        
       
        boolean[] visited = new boolean[n];
        int minSwaps = 0;
        
        for (int i = 0; i < n; i++) {
            if (visited[i] || targetPos[i] == i) {
                continue;
            }
            
            
            int cycleSize = 0;
            int curr = i;
            while (!visited[curr]) {
                visited[curr] = true;
                curr = targetPos[curr]; 
                cycleSize++;
            }
            
           
            if (cycleSize > 1) {
                minSwaps += (cycleSize - 1);
            }
        }
        
        return minSwaps;
    }
    
    
    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
