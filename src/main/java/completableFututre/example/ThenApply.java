package completableFututre.example;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


//return String result
public class ThenApply {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        CompletableFuture<String> thanApplyColback = new ThenApply().collBack();
//        System.out.println(thanApplyColback.get());

        System.out.println(new ThenApply().fewThenApply().join());

    }

    // Создаём CompletableFuture
    public CompletableFuture<String> tryThenApply() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("" + e.getMessage());
            }
            System.out.println("Я работаю... ");

            return "from tryTheApply";
        });
    }

    // Добавляем колбэк к Future, используя thenApply()
    //  return String result
    public CompletableFuture<String> collBack() {
        return tryThenApply().thenApply(name -> {
            return "hi " + name;
        });
    }

    //few thanApply()
    public CompletableFuture<String> fewThenApply() {
        return CompletableFuture.supplyAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        System.out.println("" + e.getMessage());
                    }
                    System.out.println("Я работаю... ");
                    return "Dzmitry";
                })
                .thenApply(name -> "Hi " + name)
                .thenApply(greatings -> "Big congrats " + greatings);
    }


}
