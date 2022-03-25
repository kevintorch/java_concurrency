package guarded_blocks.producer_consumer;

public class ProducerConsumerExample {
    public static void main(String[] args) {
        Drop drop = new Drop();
        new ProducerThread(drop).start();
        new ConsumerThread(drop).start();
    }
}
