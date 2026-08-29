class Solution:
    def lexicographicallySmallestArray(self, nums: list[int], limit: int) -> list[int]:
        n = len(nums)
    
        sorted_pairs = sorted((val, idx) for idx, val in enumerate(nums))
        
       
        groups = []
        current_group = [sorted_pairs[0]]
        
        for i in range(1, n):
            if sorted_pairs[i][0] - sorted_pairs[i - 1][0] <= limit:
                current_group.append(sorted_pairs[i])
            else:
                groups.append(current_group)
                current_group = [sorted_pairs[i]]
        groups.append(current_group)
        
        
        result = [0] * n
        for group in groups:
           
            indices = sorted(idx for val, idx in group)
           
            values = [val for val, idx in group]
            
           
            for idx, val in zip(indices, values):
                result[idx] = val
                
        return result
