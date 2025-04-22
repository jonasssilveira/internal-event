import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;


class ConsumerTest {

    private ConsumerPrint consumer;

    @BeforeEach
    void setUp() {
        consumer = new ConsumerPrint();
    }

    @Test
    public void testIfItWasConsumed(){
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        queue.offer("Hello");
        consumer.consume(queue);
        queue.offer("World");
        consumer.shutdown();
        assertTrue(queue.isEmpty());
    }


}