package workshop15.test5;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor;

public class VirtualTest {

    public static void main(String[] args) {
        try (ExecutorService executor = newVirtualThreadPerTaskExecutor()) {
            //newVirtualThreadPerTaskExecutor() 작업 당 하나의 가상 스레드를
            //생성하는 가상 스레드 전용 Executor를 만듦

            for (int i = 1; i <= 5; i++) {
                final int count = i;
                executor.submit(() -> {
                    System.out.println("가상 스레드 " + count + "번 실행 중");
                });
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            executor.shutdown();
        }
        System.out.println("main 종료");
    }
}
