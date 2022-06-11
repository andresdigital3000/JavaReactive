package ReactiveStreams;

import java.util.concurrent.Flow;

public class MainSimple {
  public static void main(String[] args) {
    new SimplePublisher(10).subscribe(new Flow.Subscriber<>() {
      @Override
      public void onSubscribe(Flow.Subscription subscription) {}

      @Override
      public void onNext(Integer item) {
        System.out.println("item = [" + item + "]");
      }

      @Override
      public void onError(Throwable throwable) {}

      @Override
      public void onComplete() {
        System.out.println("complete");
      }
    });
  }

}
