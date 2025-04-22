import java.util.concurrent.*;

public interface QueueConsumer {
    void consume(BlockingQueue<String> queue);
    ExecutorService executor = Executors.newFixedThreadPool(10);
    void stop();
    default void shutdown(){
        boolean b;
        try {
            b = executor.awaitTermination(10L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (!b) {
            stop();
            executor.shutdownNow();
        }
    }
}
