package FunctionalProgramming.function;

import java.util.function.Function;

public class FunctionUsage {
  public static void main(String ... args) {
    //Usando interfaces
    Function<Long, Long> adder = new AddThree();
    long result = adder.apply(4L);
    System.out.println(result);

    //Usando lambda expressions
    Function<Long, Long> adderExpression = (item)-> item + 3;
    long resultExpression = adderExpression.apply(2L);
    System.out.println(resultExpression);

  }

}
