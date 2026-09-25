import java.util.*;

public class Solution {
    public List<String> braceExpansionII(String expression) {
        Stack<Set<String>> operandStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

       
        Runnable evaluateTop = () -> {
            char op = operatorStack.pop();
            Set<String> set2 = operandStack.pop();
            Set<String> set1 = operandStack.pop();
            Set<String> result = new HashSet<>();

            if (op == '+') { 
                result.addAll(set1);
                result.addAll(set2);
            } else if (op == '*') { 
                for (String s1 : set1) {
                    for (String s2 : set2) {
                        result.add(s1 + s2);
                    }
                }
            }
            operandStack.push(result);
        };

        int n = expression.length();
        for (int i = 0; i < n; i++) {
            char c = expression.charAt(i);

           
            if (i > 0) {
                char prev = expression.charAt(i - 1);
                if ((Character.isLetter(prev) || prev == '}') && (Character.isLetter(c) || c == '{')) {
               
                    while (!operatorStack.isEmpty() && operatorStack.peek() == '*') {
                        evaluateTop.run();
                    }
                    operatorStack.push('*');
                }
            }

          
            if (Character.isLetter(c)) {
                Set<String> singleWordSet = new HashSet<>();
                singleWordSet.add(String.valueOf(c));
                operandStack.push(singleWordSet);
            } else if (c == '{') {
                operatorStack.push('{');
            } else if (c == ',') {
               
                while (!operatorStack.isEmpty() && operatorStack.peek() != '{') {
                    evaluateTop.run();
                }
                operatorStack.push('+');
            } else if (c == '}') {
               
                while (!operatorStack.isEmpty() && operatorStack.peek() != '{') {
                    evaluateTop.run();
                }
                operatorStack.pop();
            }
        }

       
        while (!operatorStack.isEmpty()) {
            evaluateTop.run();
        }

      
        List<String> resultList = new ArrayList<>(operandStack.pop());
        Collections.sort(resultList);
        return resultList;
    }
}
