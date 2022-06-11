package APIStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.Test;

public class APIStreamTest {

  Materia m1;
  Materia m2;
  Materia m3;
  Materia m4;

  Alumno a1;
  Alumno a2;
  Alumno a3;

  private Stream<Alumno> stream;

  public static void generateDouble() {
    // using Stream.generate() method
    // to generate 5 random Integer values
    Stream.generate(new Random()::nextDouble).limit(8).forEach(System.out::println);
  }

  @BeforeAll
  public void init() {
    m1 = new Materia("111", "Ciencias Politicas");
    m2 = new Materia("222", "Economia");
    m3 = new Materia("333", "Historia");
    m4 = new Materia("444", "Psicologia");

    a1 = new Alumno("Rodolfo Hernandez", 29, List.of(m1, m2));
    a2 = new Alumno("Federico Gutierrez", 23, List.of(m3, m4));
    a3 = new Alumno("Gustavo Petro", 29, List.of(m1, m2, m4));

    stream = Stream.<Alumno>builder().add(a1).add(a2).add(a3).build();


  }

  @Test
  public void emptyStream() {
    List<String> list = new ArrayList<>();
    Stream<String> stream = list == null || list.isEmpty() ? Stream.empty() : list.stream();

  }

  @Test
  public void collectionStream() {
    init();
    List<Alumno> alumnos = new ArrayList<>();
    alumnos.add(a1);
    alumnos.add(a2);
    alumnos.add(a3);
    alumnos.stream().forEach(System.out::println);
    //stream forEach(item -> System.out.println(item));

    //return alumnos.stream();
  }

  @Test
  public void arrStream() {
    init();
    Alumno[] alumnos = new Alumno[] {a1, a2, a3};
    Arrays.stream(alumnos).forEach(System.out::println);

  }

  @Test
  public void filterOperationWithArrStream() {
    List<String> lista = Arrays.asList("hola", "que", "tal", "estas", "tu");
    lista.stream().filter((cadena) -> cadena.length() > 3).map((cadena) -> cadena.toUpperCase()).forEach(System.out::println);
  }

  @Test
  public void filterOperationWithArrStreamLogging() {
    List<String> lista = Arrays.asList("hola", "que", "tal", "estas", "tu");
    lista.stream().peek((cadena) -> {
      System.out.println("***inicio****");
      System.out.println(cadena);
      System.out.println("****fin inicio****");
    }).filter((cadena) -> cadena.length() > 3).peek((cadena) -> {
      System.out.println("-----filtro--------");
      System.out.println(cadena);
      System.out.println("-----fin filtro-----");
    }).map((cadena) -> cadena.toUpperCase()).peek((cadena) -> {
      System.out.println(">>>>>>mayusculas>>>>>>");
      System.out.println(cadena);
      System.out.println(">>>>>>>fin mayusculas>>>>>>");
    }).forEach(System.out::println);
  }

  @Test
  public void filterWithAnonymousClass() {
    init();
    stream = Stream.<Alumno>builder().add(a1).add(a2).add(a3).build();
    stream.filter(new Predicate<Alumno>() {
      @Override
      public boolean test(Alumno alumno) {
        return alumno.getEdad() == 29;
      }
    }).forEach(System.out::println);

  }

  @Test
  public void filterWithLambdaExpression() {
    init();
    stream.filter(alumno -> alumno.edad == 29).forEach(alumno -> System.out.println(alumno));
  }

  @Test
  public void flatMapOperation() {
    init();
    Stream<Materia> materiaStream = stream.flatMap(alumno -> alumno.getMaterias().stream()).distinct();
    System.out.println(materiaStream.count());
    //System.out.println(materiaStream.count());
  }

  @Test
  public void flatMapOperationDistinct() {
    int[] array = {1, 2, 3, 4, 3, 6};

    //Stream<int[]>
    Stream<int[]> streamArray = Stream.of(array);

    //Stream<int[]> -> flatMap -> IntStream
    IntStream intStream = streamArray.flatMapToInt(x -> Arrays.stream(x)).distinct();

    intStream.forEach(x -> System.out.println(x));
  }

  @Test
  public void flatMapOperationFirst() {
    int[] array = {1, 2, 3, 4, 3, 6};

    //Stream<int[]>
    Stream<int[]> streamArray = Stream.of(array);

    //Stream<int[]> -> flatMap -> IntStream
    OptionalInt intStream = streamArray.flatMapToInt(x -> Arrays.stream(x)).findFirst();

    System.out.println(intStream.getAsInt());
  }

  @Test
  public void flatMapOperationFirstParallel() {
    int[] array = {1, 2, 3, 4, 3, 6};

    //Stream<int[]>
    Stream<int[]> streamArray = Stream.of(array);

    //Stream<int[]> -> flatMap -> IntStream
    OptionalInt intStream = streamArray.flatMapToInt(x -> Arrays.stream(x)).findAny();

    System.out.println(intStream.getAsInt());
  }

  @Test
  public void generateInt() {
    // using Stream.generate() method
    // to generate 5 random Integer values
    Stream.generate(new Random()::nextInt).limit(7).forEach(System.out::println);
  }


}
