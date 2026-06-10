from heapq import heappush, heappop
from typing import List

class Solution:
    def maxTotalValue(self, nums: List[int], k: int) -> int:
        n = len(nums)
        
        lg = [0] * (n + 1)
        for i in range(2, n + 1):
            lg[i] = lg[i >> 1] + 1
            
        max_log = lg[n] + 1
        f_max = [[0] * max_log for _ in range(n)]
        f_min = [[0] * max_log for _ in range(n)]
        
        for i in range(n):
            f_max[i][0] = nums[i]
            f_min[i][0] = nums[i]
            
        for j in range(1, max_log):
            for i in range(n - (1 << j) + 1):
                f_max[i][j] = max(f_max[i][j - 1], f_max[i + (1 << (j - 1))][j - 1])
                f_min[i][j] = min(f_min[i][j - 1], f_min[i + (1 << (j - 1))][j - 1])
                
        def query_max(l: int, r: int) -> int:
            g = lg[r - l + 1]
            return max(f_max[l][g], f_max[r - (1 << g) + 1][g])
            
        def query_min(l: int, r: int) -> int:
            g = lg[r - l + 1]
            return min(f_min[l][g], f_min[r - (1 << g) + 1][g])
            
        pq = []
        for l in range(n):
            val = query_max(l, n - 1) - query_min(l, n - 1)
            heappush(pq, (-val, l, n - 1))
            
        ans = 0
        for _ in range(k):
            val, l, r = heappop(pq)
            ans += -val
            if r > l:
                next_val = query_max(l, r - 1) - query_min(l, r - 1)
                heappush(pq, (-next_val, l, r - 1))
                
        return ans
