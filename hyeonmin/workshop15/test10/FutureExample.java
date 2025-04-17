package workshop15.test10;

import java.util.concurrent.CompletableFuture;

public class FutureExample {

    public static void main(String[] args) {
        System.out.println("비동기 계산 시작");

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int result = 200 + 100;
            System.out.println("최초 계산 결과: " + result);
            return result;
        }).thenApply(result -> {
            int twice = result * 2;
            System.out.println("후속 처리 결과 (x2): " + twice);
            return twice;
        });

        future.join();

        System.out.println("main 종료");
    }
}
