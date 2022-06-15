import java.math.BigInteger;
import java.util.Scanner;

public class FactorialSum {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter num: ");
        int num = in.nextInt();

        BigInteger numFactorial = factorial(num);  //find factorial (BigInteger because it doesn't have upper limit)
        int digitsSum = sumOfDigits(numFactorial);  //find the sum of digits of the factorial

        System.out.println("Sum of digits of "+num+"! = " +digitsSum);
    }

    public static BigInteger factorial(int num){
        if(num == 0){
            return new BigInteger("1");
        }
        return new BigInteger(Integer.toString(num)).multiply(factorial(num-1));  //default recursive factorial but with BigInteger operations
    }

    public static int sumOfDigits(BigInteger num){
        int sum = 0;

        BigInteger zero = new BigInteger("0");
        BigInteger divider = new BigInteger("10");

        while(num.compareTo(zero) > 0){  //while num > 0
            sum += num.remainder(divider).intValue();  //add every digit to sum (use remainder of the division)
            num = num.divide(divider);  //divide number by 10 to go to the next digit
        }

        return sum;
    }
}
