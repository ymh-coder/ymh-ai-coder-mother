import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;


@SpringBootTest
public class test4 {
    public static void main(String[] args) {
        CompletableFuture<String> result = getUserInfo(1)
                .thenCompose(user -> getProductInfo("P1001")
                        .thenApply(product -> "用户[" + user + "]购买了商品[" + product + "]"))
                .exceptionally(ex -> "下单流程失败：" + ex.getMessage());

        System.out.println(result.join());
    }

    static CompletableFuture<String> getUserInfo(int userId) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(500);
            return "张三";
        });
    }

    static CompletableFuture<String> getProductInfo(String productId) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(500);
            return "机械键盘";
        });
    }

    static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
