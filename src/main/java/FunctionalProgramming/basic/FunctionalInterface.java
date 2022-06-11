package FunctionalProgramming.basic;

public interface FunctionalInterface {
  void execute();
  default void print(String text) {
    System.out.println(String.format("Before execute: %s ", text));
    execute();
  }

}
