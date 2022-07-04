package completableFututre.example;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Merge2CF {

    /*Чтобы избавиться от вложенного Future
    CompletableFuture<CompletableFuture<Double>> result
    , используйте метод thenCompose():*/

    /*thenCombine() используется, когда вы хотите,
     чтобы две задачи работали независимо друг от друга
     и по завершению обоих выполнялось какое-нибудь действие.*/

    public static void main(String[] args) {
//        System.out.println(new Merge2CF().merge1And2("test string").join());
        //____________________________________________________________________________
        System.out.println(new Merge2CF().calculateWithMerge().join().doubleValue());

    }

    public CompletableFuture<String> create1(String firstService) {

        return CompletableFuture.supplyAsync(() -> {
            return "result from UserService" + firstService;
        });
    }

    public CompletableFuture<String> create2(String secondService) {

        return CompletableFuture.supplyAsync(() -> {
            return "result from Animal service " + secondService;
        });
    }

    public CompletableFuture<String> merge1And2(String someString1) {
        return create1(someString1).thenCompose(fromUserService -> create2(fromUserService));

    }

    //_________________________________________________________________________________________________________________


    /*Если thenCompose() используется для объединения двух задач, когда одна зависит от другой,
     то thenCombine() используется, когда вы хотите, чтобы две задачи работали независимо друг от друга
     и по завершению обоих выполнялось какое-нибудь действие.*/

    public CompletableFuture<Double> getWeightInKgFuture() {
        System.out.println("Получение веса.");
        CompletableFuture<Double> weightInKgFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 65.0;
        });
        return weightInKgFuture;
    }

    public CompletableFuture<Double> getHeight() {
        System.out.println("Получение роста.");
        CompletableFuture<Double> heightInCmFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 177.8;
        });
        return heightInCmFuture;
    }

    public CompletableFuture<Double> calculateWithMerge() {

        System.out.println("Расчёт индекса массы тела.");
        CompletableFuture<Double> combinedFuture = getWeightInKgFuture()
                .thenCombine(getHeight(), (weightInKg, heightInCm) -> {
                    Double heightInMeter = heightInCm / 100;
                    return weightInKg / (heightInMeter * heightInMeter);
                });
        return combinedFuture;
    }
}
