class Solution {
    public int totalNumbers(int[] digits) {
        
        int[] count = new int[10];
        for (int d : digits) {
            count[d]++;
        }

        int validCount = 0;

        for (int i = 100; i <= 998; i += 2) {
            int hundreds = i / 100;
            int tens = (i / 10) % 10;
            int ones = i % 10;

           
            int[] temp = new int[10];
            temp[hundreds]++;
            temp[tens]++;
            temp[ones]++;

          
            if (temp[hundreds] <= count[hundreds] && 
                temp[tens] <= count[tens] && 
                temp[ones] <= count[ones]) {
                validCount++;
            }
        }

        return validCount;
    }
}
