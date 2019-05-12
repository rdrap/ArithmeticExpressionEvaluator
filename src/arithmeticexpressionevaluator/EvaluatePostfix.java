package arithmeticexpressionevaluator;

public class EvaluatePostfix {

    public static String postfix;
    public static String[] postfixArr;
    public static Stack stack = new Stack<String>();

    public EvaluatePostfix(String postfix) {
        this.postfix = postfix;
    }

    public Double evaluate() {
        postfixArr = postfix.split(" ");
        for (String s : postfixArr) {
            if (!isOperator(s)) {
                stack.push(s);
            } else {
                double result = 0;
                double operand1 = Double.valueOf(stack.getSecond().toString());
                double operand2 = Double.valueOf(stack.getTop().toString());
                switch (s) {
                    case "+":
                        result = operand1 + operand2;
                        break;
                    case "-":
                        result = operand1 - operand2;
                        break;
                    case "*":
                        result = operand1 * operand2;
                        break;
                    case "/":
                        result = operand1 / operand2;
                        break;
                }
                stack.remove();
                stack.remove();
                stack.push(result);
            }
        }
        return Double.valueOf(stack.getTop().toString());
    }

    public static boolean isOperator(String s) {
        return (s.equals("(") || s.equals(")") || s.equals("+")
                || s.equals("-") || s.equals("*") || s.equals("/"));
    }

}
