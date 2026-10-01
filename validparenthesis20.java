import java.util.Stack;
import java.util.Map;
import java.util.HashMap;

class Solution {
    public boolean isValid(String s) {
      
        Map<Character, Character> matchingBrackets = new HashMap<>();
        matchingBrackets.put(')', '(');
        matchingBrackets.put('}', '{');
        matchingBrackets.put(']', '[');

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            
            if (matchingBrackets.containsKey(c)) {
              
                char topElement = stack.isEmpty() ? '#' : stack.pop();

               
                if (topElement != matchingBrackets.get(c)) {
                    return false;
                }
            } else {
            
                stack.push(c);
            }
        }

    
        return stack.isEmpty();
    }
}
