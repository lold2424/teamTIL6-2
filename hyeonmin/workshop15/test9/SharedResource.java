package workshop15.test9;

import java.util.concurrent.locks.ReentrantLock;

public class SharedResource implements Runnable {
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        safeAccess();
    }

    public void safeAccess() {
        lock.lock();

        try {
            System.out.println("잠금 획득");
        } finally {
            System.out.println("잠금 해제");
            lock.unlock();
        }
    }
}
