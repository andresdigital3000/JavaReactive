package ObservableYSuscriber;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class ObservableMain {
  private static int init = 1;
  private static int fin = 5;

  public static void main(String[] args) {
    //createObservableBasic();
    //createObservableJust();
    //createObservableFromIterable();
    //createObservableRange();
    //createObservableInterval();
    //createObservableDefer();
    //observableBufferExample();
    //transformingObservables();
    observableFlatMap();
  }

  public static void createObservableBasic() {
    Observable<String> source = Observable.create(emitter -> {
      try {
        emitter.onNext("Uno");
        emitter.onNext("Dos");
        emitter.onNext("Tres");
        emitter.onComplete();
      } catch (Throwable e) {
        emitter.onError(e);
      }
    });
    source.subscribe(s -> System.out.println("Numero recibido: " + s), Throwable::printStackTrace);
  }

  public static void createObservableJust() {
    Observable<String> source = Observable.just("Uno", "Dos", "Tres");
    source.subscribe(s -> System.out.println("Numero recibido: "+s));

  }

  public static void createObservableFromIterable() {
    List<String> items = Arrays.asList("Uno", "Dos", "Tres");
        Observable<String> source = Observable.fromIterable(items);
    source.subscribe(s -> System.out.println("Numero recibido: " + s));

  }

  public static void createObservableRange() {
    Observable.range(0, 2)
              .subscribe(s -> System.out.println("Rango recibido: "+s));

  }

  public static void createObservableInterval() {

    Observable.interval(1, TimeUnit.SECONDS)
      .subscribe(s -> System.out.println(s + " Mississippi"));
    Observable.interval(5, TimeUnit.SECONDS, Schedulers.io())
             .subscribe(tick -> System.out.println("tick = "+tick));
    Schedulers.shutdown();
  }

  public static void createObservableDefer() {
    Observable<Integer> source = Observable.defer (() -> Observable.range(init, fin));
    source.subscribe(i -> System.out.println("Observer 1: "+i));
    fin = 10;
    source.subscribe(i -> System.out.println("Observer 2: " + i));
  }


  public static void observableBufferExample() {
      String[] persons = new String[] {"Joe", "Jane", "John", "Phil"};
      Observable.fromArray(persons)
                .buffer(2, 1)
                .subscribe((personList)->{System.out.println(personList);});

  }

  public static void transformingObservables() {
    System.out.println("--------map------------------------");
    Observable<Integer> observable = Observable.range(1, 3);
    observable.map(i -> "String: "+i)
              .subscribe(
                  result -> System.out.println(result),
                  e -> System.out.println("Error: " + e),
                  () -> System.out.println("Completed")
              );
    System.out.println("--------flatmap------------------------");
    observable = Observable.range(1, 3);
    observable.flatMap( i -> Observable.range(0,i))
              .subscribe(
                  result -> System.out.println(result),
                  e -> System.out.println("Error: " + e),
                  () -> System.out.println("Completed")
              );
    System.out.println("--------buffer ------------------------");
    observable = Observable.range(0, 5);
    observable.buffer(2)
              .subscribe(
                  list -> System.out.println(list),
                  e -> System.out.println("Error: " + e),
                  () -> System.out.println("Completed")
              );
    observable = Observable.range(0, 10);
    observable.buffer(3,2)
              .subscribe(
                  list -> System.out.println(list),
                  e -> System.out.println("Error: " + e),
                  () -> System.out.println("Completed")
              );
    System.out.println("--------group by ------------------------");
    observable = Observable.range(0, 10);
    observable.groupBy(v ->  v % 2 == 0)
              .subscribe(
                  group -> group.takeLast(1).subscribe(v-> System.out.println(v)),
                  e -> System.out.println("Error: " + e),
                  () -> System.out.println("Completed")
              );

  }

  public static void observableFlatMap(){
    Observable<Integer> source = Observable.just(1,2,3);
    Observable<Integer> source2 = Observable.just(4,5,6);

    Observable combinado = Observable.create(emmiter -> {
      emmiter.onNext(source);
      emmiter.onNext(source2);
    });

    combinado.subscribe(datos -> System.out.println(datos));
  }

}

