import ReactiveStreams.SimplePublisher;
import java.util.concurrent.Flow;
import org.reactivestreams.tck.TestEnvironment;
import org.reactivestreams.tck.flow.FlowPublisherVerification;

public class SimplePublisherTest extends FlowPublisherVerification<Integer> {

  public SimplePublisherTest() {
    super(new TestEnvironment());
  }

  @Override
  public Flow.Publisher<Integer> createFlowPublisher(long elements) {
    return new SimplePublisher((int) elements);
  }

  @Override
  public Flow.Publisher<Integer> createFailedFlowPublisher() {
    return null;
  }
}