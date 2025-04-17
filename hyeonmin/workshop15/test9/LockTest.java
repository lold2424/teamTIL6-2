package workshop15.test9;

public class LockTest {

    public static void main(String[] args) {
        SharedResource res = new SharedResource();

        Thread t1 = new Thread(res::safeAccess);
        Thread t2 = new Thread(res::safeAccess);

        t1.start();
        t2.start();
    }
}
