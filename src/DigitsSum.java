import java.util.*;

public class DigitsSum {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int N = 0;
        boolean flag = false;

        while(!flag){
            System.out.print("\nEnter N: ");
            try{
                N = Integer.parseInt(in.nextLine());
                if(N < 1) throw new NegativeInputException("Number must be positive!");
                flag = true;
            }
            catch (NumberFormatException | NegativeInputException e){
                e.printStackTrace();
            }
        }

        Set<String> possibleExpressions = findExpressions(N);  //generating all possible expressions

        System.out.println(possibleExpressions);
        System.out.println(countRightExpressions(possibleExpressions));
    }

    public static Set<String> findExpressions(int N){
        Set<String> expressions = new HashSet<>();

        int possibleExpressions = (int)Math.pow(2*N, 2);  //number of possible expressions

        Random random = new Random();

        while(expressions.size() != possibleExpressions){
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 2*N; i++) {
                sb.append(random.nextInt(2) == 0 ? "(" : ")");
            }
            expressions.add(sb.toString());  //generate an expression
        }
        return expressions;
    }

    public static int countRightExpressions(Set<String> expressions){
        int countRight = 0;  //right expressions
        int countOpen, countClose;  //open and close brackets counters

        for(String expression : expressions){
            countOpen = 0;
            countClose = 0;
            for (int i = 0; i < expression.length(); i++) {
                if(expression.charAt(i) == '('){
                    countOpen++;  //count open brackets
                }
                else countClose++;  //count close brackets
                if(countClose > countOpen){  //make sure that number of close brackets not bigger than open
                    break;
                }
                if(i == expression.length()-1 && countOpen == countClose){  //if number of open equals number of close brackets
                    countRight++;  //increment counter
                }
            }
        }
        return countRight;
    }
}