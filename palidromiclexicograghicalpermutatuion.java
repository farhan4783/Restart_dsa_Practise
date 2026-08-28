import java.util.Arrays;

public class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        int oddCount = 0;
        int oddCharIdx = -1;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] % 2 != 0) {
                oddCount++;
                oddCharIdx = i;
            }
        }

       
        if (oddCount > 1) {
            return "";
        }

        int halfLen = n / 2;
        int[] halfCnt = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCnt[i] = cnt[i] / 2;
        }

       
        for (int prefixLen = halfLen; prefixLen >= 0; prefixLen--) {
            int[] currentHalfCnt = halfCnt.clone();
            boolean validPrefix = true;
            char[] prefixChars = new char[prefixLen];

            for (int i = 0; i < prefixLen; i++) {
                int charIdx = target.charAt(i) - 'a';
                if (currentHalfCnt[charIdx] > 0) {
                    currentHalfCnt[charIdx]--;
                    prefixChars[i] = target.charAt(i);
                } else {
                    validPrefix = false;
                    break;
                }
            }

            if (!validPrefix) {
                continue;
            }

            
            if (prefixLen == halfLen) {
                StringBuilder sb = new StringBuilder();
                sb.append(new String(prefixChars));
                if (n % 2 != 0) {
                    sb.append((char) ('a' + oddCharIdx));
                }
                for (int i = halfLen - 1; i >= 0; i--) {
                    sb.append(prefixChars[i]);
                }
                String candidate = sb.toString();
                if (candidate.compareTo(target) > 0) {
                    return candidate;
                }
                continue;
            }

            
            int startChar = target.charAt(prefixLen) - 'a' + 1;
            for (int c = startChar; c < 26; c++) {
                if (currentHalfCnt[c] > 0) {
                    int[] tempCnt = currentHalfCnt.clone();
                    tempCnt[c]--;

                    char[] resHalf = new char[halfLen];
                    System.arraycopy(prefixChars, 0, resHalf, 0, prefixLen);
                    resHalf[prefixLen] = (char) ('a' + c);

                    
                    int fillIdx = prefixLen + 1;
                    for (int ch = 0; ch < 26; ch++) {
                        while (tempCnt[ch] > 0) {
                            resHalf[fillIdx++] = (char) ('a' + ch);
                            tempCnt[ch]--;
                        }
                    }

                    
                    StringBuilder sb = new StringBuilder();
                    sb.append(new String(resHalf));
                    if (n % 2 != 0) {
                        sb.append((char) ('a' + oddCharIdx));
                    }
                    for (int i = halfLen - 1; i >= 0; i--) {
                        sb.append(resHalf[i]);
                    }

                    String candidate = sb.toString();
                    if (candidate.compareTo(target) > 0) {
                        return candidate;
                    }
                }
            }
        }

        return "";
    }
}
