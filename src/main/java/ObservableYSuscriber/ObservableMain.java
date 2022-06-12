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
    observableBufferExample();
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
    Observable.range(1, 10)
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
                .buffer(2, 3)
                .subscribe((personList)->{System.out.println(personList);});

  }


}

