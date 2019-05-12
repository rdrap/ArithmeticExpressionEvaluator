//Ryan Draper, TTh 4-5pm
package arithmeticexpressionevaluator;

public class ArithmeticExpressionEvaluator {

    public static String infix = "( 14 + 8 ) * ( 8 - 4 ) / ( ( 6 - 2 * 2 ) * ( 1 + 2 ) )";
    public static String[] infixArr;
    public static String postfix = ""; // should be: 14 8 + 8 4 - * 6 2 2 * - 1 2 + * /
    public static Stack operands = new Stack<String>();

    public static void main(String[] args) {
        infixArr = infix.split(" ");
        System.out.print("Infix: ");
        for (String s : infixArr) {
            System.out.print(s);
        }
        System.out.print("\n");

        for (String s : infixArr) {
            if (!isOperator(s)) {
                postfix += (s + " ");
            } else if (s.equals(")")) {
                while (operands.size() > 0 && !operands.getTop().equals("(")) {
                    postfix += (operands.pop() + " ");
                }
                if (operands.size() > 0) {
                    operands.remove();
                }
            } else if (s.equals("(")) {
                operands.push(s);
            } else {
                while (operands.size() > 0 && !lowerPrecedence(String.valueOf(operands.getTop()), s)) {
                    postfix += (operands.pop() + " ");
                }
                operands.push(s);
            }
        }
        postfix += operands.pop();
        System.out.println("Postfix: " + postfix);
        EvaluatePostfix eval = new EvaluatePostfix(postfix);
        System.out.println("Evaluation: " + eval.evaluate());
    }

    public static boolean lowerPrecedence(String a, String b) {
        int aprec = 0;
        int bprec = 0;
        switch (a) {
            case "(":
                aprec = 0;
                break;
            case "*":
                aprec = 2;
                break;
            case "/":
                aprec = 2;
                break;
            case "+":
                aprec = 1;
                break;
            case "-":
                aprec = 1;
                break;
        }
        switch (b) {
            case "(":
                bprec = 0;
                break;
            case "*":
                bprec = 2;
                break;
            case "/":
                bprec = 2;
                break;
            case "+":
                bprec = 1;
                break;
            case "-":
                bprec = 1;
                break;
        }
        return aprec < bprec;
    }

    public static boolean isOperator(String s) {
        return (s.equals("(") || s.equals(")") || s.equals("+")
                || s.equals("-") || s.equals("*") || s.equals("/"));
    }
}

/*
SAMPLE OUTPUT:

Infix: (14+8)*(8-4)/((6-2*2)*(1+2))
Postfix: 14 8 + 8 4 - * 6 2 2 * - 1 2 + * /
Evaluation: 14.666666666666666
*/