package FunctionalProgramming.predicate;

import java.util.function.Predicate;

public class PredicateUsage {
  public static void main(String ... args) {
    //Usando interfaces
    Predicate checkNull = new CheckForNull();
    Long l = 4L;
    System.out.println(checkNull.test(l));

    //Usando lambda expressions
    Predicate checkNullExpressions = (item) -> item == null;
    String text = "hola";
    System.out.println(checkNullExpressions.test(text));


  }

}
