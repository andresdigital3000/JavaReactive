package MultiCast;

import io.reactivex.Observable;

public class MulticastMain {
  public static void main(String ... args){
    optimizedObservable();
  }

  public static void expensiveObservable(){
    Observable<String> observable = Observable.just("Event")
                                              .publish()
                                              .autoConnect(2)
                                              .map(s -> {
                                                System.out.println("Expensive operation for " + s);
                                                return s;
                                              });

    observable.subscribe(s -> System.out.println("Sub1 got: " + s));
    observable.subscribe(s -> System.out.println("Sub2 got: " + s));
  }

  public static void optimizedObservable() {
    Observable<String> observable = Observable.just("Event")
                                              .map(s -> {
                                                System.out.println("Expensive operation for " + s);
                                                return s;
                                              })
                                              .publish()
                                              .autoConnect(2);

    observable.subscribe(s -> System.out.println("Sub1 got: " + s));
    observable.subscribe(s -> System.out.println("Sub2 got: " + s));

  }

}
