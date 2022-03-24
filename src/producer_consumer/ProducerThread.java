package producer_consumer;

import java.util.Random;

public class ProducerThread extends Thread {

    private final String[] messages = new String[]{
            "Mares eat oats",
            "Does eat oats",
            "Little lambs eat ivy",
            "A kid will eat ivy too"
    };

    private final Drop drop;

    public ProducerThread(Drop drop) {
        this.drop = drop;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (String message : messages) {
            drop.put(message);
            try {
                sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        drop.put("DONE");
    }
}
