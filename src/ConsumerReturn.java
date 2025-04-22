import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

public class ConsumerReturn implements QueueConsumer {
    private final ExecutorService executor = Executors.newFixedThreadPool(10);
    private volatile boolean isRunning = true;

    public void consume(BlockingQueue<String> queue) {
        executor.submit(() -> consumer.apply(queue));
    }

    @Override
    public void stop() {
        isRunning = false;
    }

    private final Function<BlockingQueue<String>, Void> consumer = (queue) -> {
        while (isRunning) {
            try {
                System.out.println("Consume2: " + queue.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    };
}

