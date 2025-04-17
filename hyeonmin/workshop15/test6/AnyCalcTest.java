package workshop15.test6;

import java.util.concurrent.CompletableFuture;

public class AnyCalcTest {

    public static void main(String[] args) {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int result = 200 + 100;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return result;
        });

        try {
            System.out.println("계산 결과: " + future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("main 종료");
    }
}
