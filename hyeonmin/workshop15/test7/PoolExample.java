package workshop15.test7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PoolExample {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 5; i++) {
            final int count = i;
            pool.submit(() -> {
                System.out.println("작업 " + count + " 처리 중");
            });
        }

        pool.shutdown();
    }
}
