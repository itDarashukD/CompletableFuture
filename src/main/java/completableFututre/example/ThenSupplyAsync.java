package completableFututre.example;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/*Чтобы иметь больше контроля над потоком, выполняющим задачу, вы можете использовать асинхронные колбэки.
 Если вы используете thenApplyAsync(),
он будет выполнен в другом потоке, полученном из ForkJoinPool.commonPool():*/
public class ThenSupplyAsync {

    public static void main(String[] args) {
//        System.out.println(new ThenSupplyAsync().trySupplyAsync().join());
        System.out.println(new ThenSupplyAsync().trySupplyAsyncWithExecutorPool().join());

    }

    public CompletableFuture<String> trySupplyAsync() {
        return CompletableFuture.supplyAsync(() -> {
            return "Некоторый результат";
        }).thenApplyAsync(result -> {
            // Выполняется в другом потоке, взятом из ForkJoinPool.commonPool()
            return "Обработанный результат +" + result;
        });
    }

    /*Более того, если вы передадите Executor в thenApplyAsync(),
    задача будет выполнена в потоке, полученном из пула потоков Executor.*/

    public CompletableFuture<String> trySupplyAsyncWithExecutorPool() {
        Executor executor = Executors.newFixedThreadPool(2);

        return CompletableFuture.supplyAsync(() -> {
            return "Некоторый результат";
        }).thenApplyAsync(result -> {
            // Выполняется в потоке, полученном от Executor
            return "Обработанный результат " +result;
        },executor);
    }

}
