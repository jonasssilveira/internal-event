import java.util.concurrent.BlockingQueue;
import java.util.function.Function;

public class ConsumerPrint implements QueueConsumer{
    private volatile boolean isRunning = true;
    public void consume(BlockingQueue<String> queue) {
        executor.submit(()->consumer.apply(queue));
    }

    @Override
    public void stop() {
        isRunning = false;
    }

    private final Function<BlockingQueue<String>, Void> consumer = (queue) -> {
        while (isRunning){
            try {
                System.out.println("Consumed1: " + queue.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    };
}

