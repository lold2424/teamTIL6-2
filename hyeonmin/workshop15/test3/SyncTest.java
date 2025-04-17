package workshop15.test3;

public class SyncTest {

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Runnable plus = () -> {
            for (int i = 0; i < 500; i++) {
                counter.increment();
            }
        };

        Thread t1 = new Thread(plus);
        Thread t2 = new Thread(plus);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("최종 카운터 값: " + counter.getCount());
    }
}
