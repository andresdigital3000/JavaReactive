package FunctionalProgramming.consumer;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Example1 {
  public static void main (String ... args) {
    Consumer<Integer> consumer = (i) -> System.out.println("*" + i);
    List<Integer> integerList = Arrays.asList(1, 10, 200, 101, -10, 0);
    for ( Integer integer : integerList) {
      consumer.accept(integer);
    }


  }

}
