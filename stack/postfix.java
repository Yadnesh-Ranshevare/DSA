import java.util.ArrayList;
import java.util.Stack;

public class Postfix {

    // Function to check precedence of operators
    public static int precedence(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

    public static String Postfix(String str) {
        Stack<Character> s = new Stack<>();
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            // If character is an operand, add it to the result
            if (Character.isLetterOrDigit(c)) {
                ans.append(c);
            }
            // If the character is an opening parenthesis, push it onto the stack
            else if (c == '(') {
                s.push(c);
            }
            // If the character is a closing parenthesis, pop from the stack until '(' is found
            else if (c == ')') {
                while (!s.isEmpty() && s.peek() != '(') {
                    ans.append(s.pop());
                }
                s.pop();  // Pop the '('
            }
            // If the character is an operator, pop operators from the stack based on precedence
            else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
                while (!s.isEmpty() && precedence(s.peek()) >= precedence(c) && s.peek() != '(') {
                    ans.append(s.pop());
                }
                s.push(c);  // Push the current operator onto the stack
            }
        }

        // Pop all remaining operators from the stack
        while (!s.isEmpty()) {
            ans.append(s.pop());
        }

        return ans.toString();
    }

    public static void main(String[] args) {
        String str = "a+b/c^d+e*(f+j)";
        String ans = Postfix(str);
        System.out.println(ans);  // Output: abc^/d+e*fj+*
    }
}
