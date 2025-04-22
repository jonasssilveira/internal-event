import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        queue.offer("a");
        List<QueueConsumer> consumers = List.of(new ConsumerPrint(), new ConsumerReturn());
        consumers.forEach(consumer -> consumer.consume(queue));
        queue.offer("b");
        queue.offer("c");
        queue.offer("d");
        consumers.forEach(QueueConsumer::shutdown);
    }
}