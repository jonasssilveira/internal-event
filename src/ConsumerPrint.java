import java.util.AbstractQueue;
import java.util.concurrent.*;
import java.util.function.Function;

public class ConsumerPrint implements QueueConsumer{
    private final ExecutorService executor = Executors.newFixedThreadPool(10);
    private volatile boolean isRunning = true;
    public void consume(BlockingQueue<String> queue) {
          executor.submit(()->consumer.apply(queue));
    }
    private final Function<BlockingQueue<String>, Void> consumer = (queue) -> {
        while (isRunning){
            try {
                String item = queue.take(); // blocks until item is available
                System.out.println("Consumed: " + item);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    };
    public void shutdown() {
        isRunning = false;
        executor.shutdown();
    }
}

