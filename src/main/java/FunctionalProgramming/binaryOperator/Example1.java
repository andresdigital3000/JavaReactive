package FunctionalProgramming.binaryOperator;

import java.util.function.BinaryOperator;

public class Example1 {

  public static void main(String ... args) {
    BinaryOperator<Integer> operator1 = (a, b) -> a + b;
    System.out.println(operator1.apply(5, 7));

    BinaryOperator<Integer> operator2 = (a, b) -> a * b;
    System.out.println(operator2.apply(5, 7));

    BinaryOperator<String> operator3 = (a, b) -> a + b;
    System.out.println(operator3.apply( "java", " reactivo"));

  }
}
