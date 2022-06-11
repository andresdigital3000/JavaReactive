package FunctionalProgramming.basic;

public class FunctionalInterfaceUsase {
  public static void main(String ... args) {
    FunctionalInterface myInterface = () -> System.out.println("Processing");
    myInterface.print("Hola");
  }

}
