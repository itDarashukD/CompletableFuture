package completableFututre.example;

import java.util.concurrent.*;

public class RunAsync_SupplyAsync {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        new RunAsync_SupplyAsync().runAsync();
        String join = new RunAsync_SupplyAsync().supplyAsync().join();
        String joinWithPool = new RunAsync_SupplyAsync().supplyAsyncWithPoolExecutor().join();

        System.out.println(joinWithPool);

    }

    //don't return any result
    public void runAsync() throws ExecutionException, InterruptedException {

        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("" + e.getMessage());
            }
            System.out.println("Я буду работать в отдельном потоке, а не в главном.");

        });
        System.out.println(completableFuture.get());
    }

    //  return String result
    public CompletableFuture<String> supplyAsync () {

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("" + e.getMessage());
            }
            return "return String result";
        });
        return stringCompletableFuture;
    }


    //  return String result from new thread from poolExecutor
    public CompletableFuture<String> supplyAsyncWithPoolExecutor() {

        Executor executor = Executors.newFixedThreadPool(10);

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("" + e.getMessage());
            }
            return "return String result from new thread from poolExecutor";
        },executor);
        return stringCompletableFuture;
    }
}
