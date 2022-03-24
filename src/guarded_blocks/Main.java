package guarded_blocks;

public class Main {
    public static void main(String[] args) {

    }

    private boolean joy;
    void guardedJoy() {
        // Simple loop guard. Wastes
        // processor time. Don't do this!
        while (!joy) {}
        System.out.println("Joy has been achieved!");
    }

    synchronized void guardedJoy2() {
        while (!joy) {
            try {
                wait();
            } catch (InterruptedException ignored) {}
        }
        System.out.println("Joy and efficiency have been achieved!");
    }

    synchronized void notifyJoy() {
        joy = true;
        notifyAll();
    }
}
