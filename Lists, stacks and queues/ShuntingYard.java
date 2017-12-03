/**
 * Created by Nishant Shekhar (nxs167130)
 *            Anusha Agasthi  (nxa162430)
 *            Prince Patel    (pap160930)
 */
package cs6301.g29;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;
import java.util.Queue;

public class ShuntingYard {
    // Associativity constants for operators
    private static final int LEFT_ASSOC = 0;
    private static final int RIGHT_ASSOC = 1;

    // Supported operators
    private static final Map<String, int[]> OPERATORS = new HashMap<String, int[]>();
    static {
        // Map<"token", []{precendence, associativity}>
        OPERATORS.put("+", new int[] { 0, LEFT_ASSOC });
        OPERATORS.put("-", new int[] { 0, LEFT_ASSOC });
        OPERATORS.put("*", new int[] { 5, LEFT_ASSOC });
        OPERATORS.put("/", new int[] { 5, LEFT_ASSOC });
        OPERATORS.put("%", new int[] { 5, LEFT_ASSOC });
        OPERATORS.put("^", new int[] { 10, RIGHT_ASSOC });
        OPERATORS.put("!", new int[] { 15, LEFT_ASSOC });
    }

    /**
     * function to test whether a token is an operator or not .
     * @param token The token to be tested .
     * @return True if token is an operator . Otherwise False .
     */
    private static boolean isOperator(String token) {
        return OPERATORS.containsKey(token);
    }

    /**
     * function to the associativity of a certain operator token .
     * @param token The token to be tested (needs to operator).
     * @param type LEFT_ASSOC or RIGHT_ASSOC
     * @return True if the tokenType equals the input parameter type .
     */
    private static boolean isAssociative(String token, int type) {
        if (!isOperator(token)) {
            throw new IllegalArgumentException("Invalid token: " + token);
        }
        if (OPERATORS.get(token)[1] == type) {
            return true;
        }
        return false;
    }

    /**
     * function to compare the precedence of two operators.
     * @param token1 The first operator .
     * @param token2 The second operator .
     * @return A negative number if token1 has a smaller precedence than token2,
     * 0 if the precedences of the two tokens are equal, a positive number
     * otherwise.
     */
    private static final int cmpPrecedence(String token1, String token2) {
        if (!isOperator(token1) || !isOperator(token2)) {
            throw new IllegalArgumentException("Invalied tokens: " + token1
                    + " " + token2);
        }
        return OPERATORS.get(token1)[0] - OPERATORS.get(token2)[0];
    }

    public static String[] infixToPostfix(String[] inputTokens) {
        Queue<String> out = new LinkedList<String>();
        Stack<String> stack = new Stack<String>();
        // Read all the tokens
        for (String token : inputTokens) {
            if (isOperator(token)) {
                // If token is an operator 
                while (!stack.empty() && isOperator(stack.peek())) {
                      if ((isAssociative(token, LEFT_ASSOC) && cmpPrecedence(
                            token, stack.peek()) <= 0)
                            || (isAssociative(token, RIGHT_ASSOC) && cmpPrecedence(
                            token, stack.peek()) < 0)) {
                        out.add(stack.pop()); 	
                        continue;
                    }
                    break;
                }
                // Push the new operator on the stack
                stack.push(token);
            } else if (token.equals("(")) {
                stack.push(token); 
            } else if (token.equals(")")) {
                
                while (!stack.empty() && !stack.peek().equals("(")) {
                    out.add(stack.pop());
                }
                stack.pop(); 
            } else {
                out.add(token); 
            }
        }
        while (!stack.empty()) {
            out.add(stack.pop()); 
        }
        String[] output = new String[out.size()];
        return out.toArray(output);
    }

    public static void main(String[] args) {
        String[] input = "( 1 + 2 ) * ( 3 / 4 ) ^ ( 5 + 6 )".split(" ");
        String[] output = infixToPostfix(input);
        for (String token : output) {
            System.out.print(token + " ");
        }
    }
}