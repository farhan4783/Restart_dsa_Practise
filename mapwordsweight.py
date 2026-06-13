class Solution:
    def mapWordWeights(self, words: list[str], weights: list[int]) -> str:
        ans = []
        for word in words:
            total_weight = sum(weights[ord(c) - ord('a')] for c in word)
            remainder = total_weight % 26
            ans.append(chr(ord('z') - remainder))
        return "".join(ans)
