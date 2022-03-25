package high_level_concurrency.lock_objects;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Friend {
    private final String name;
    private final Lock lock = new ReentrantLock();

    public Friend(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean impendingBow(Friend bower) {
        boolean myLock = false;
        boolean yourLock = false;
        try {
            myLock = lock.tryLock();
            yourLock = bower.lock.tryLock();
        } finally {
            if (!(myLock && yourLock)) {
                if (myLock) {
                    lock.unlock();
                }
                if (yourLock) {
                    bower.lock.unlock();
                }
            }
        }
        return myLock && yourLock;
    }

    public void bow(Friend bower) {
        if (impendingBow(bower)) {
            try {
                bowInternal(bower);
                bower.bowBack(this);
            } finally {
                lock.unlock();
                bower.lock.unlock();
            }
        } else {
            System.out.format("%s: %s started"
                    + " to bow to me, but saw that"
                    + " I was already bowing to"
                    + " him.%n", this.name, bower.getName());
        }
    }

    private void bowInternal(Friend bower) {
        System.out.format("%s: %s has bowed to me!%n", this.name, bower.getName());
    }

    public void bowBack(Friend bower) {
        bowInternal(bower);
    }
}
