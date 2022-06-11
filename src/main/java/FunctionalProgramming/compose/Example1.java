package FunctionalProgramming.compose;

import java.util.function.Function;

public class Example1 {
  public static void main(String ... args) {
    Function<Integer, Integer> multiply = (value) -> value * 2;
    Function<Integer, Integer> add = (value) -> value + 3;
    Function<Integer, Integer> substract = (value) -> value - 1;

    Function<Integer, Integer> addThenMultiply = multiply.compose(add).andThen(substract);
    Integer result = addThenMultiply.apply(3);
    System.out.println(result);
  }

}
