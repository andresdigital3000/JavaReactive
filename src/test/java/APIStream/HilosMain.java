package APIStream;

import java.util.stream.IntStream;

public class HilosMain {
  public static void main(String ... args) {
    long start = System.currentTimeMillis();
    int total = IntStream.range(1, 10).map(HilosMain::duplicar).sum();
    long end = System.currentTimeMillis();
    System.out.println(String.format("Diferencia sin hilos %s", end - start));
    System.out.println(String.format("Número total: %s ", total));

    start = System.currentTimeMillis();
    total = IntStream.range(1, 10).parallel().map(HilosMain::duplicar).sum();
    end = System.currentTimeMillis();
    System.out.println(String.format("Diferencia con hilos %s", end - start));
    System.out.println(String.format("Número total: %s ", total));


  }

  public static int duplicar(int numero) {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return numero * 2;
  }
}
