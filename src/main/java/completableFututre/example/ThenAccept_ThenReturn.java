package completableFututre.example;

import java.util.concurrent.CompletableFuture;

public class ThenAccept_ThenReturn {

    public static void main(String[] args) {

        new ThenAccept_ThenReturn().tryThenAccept();

    }

    /*CompletableFuture.thenAccept() принимает Consumer<T> и возвращает CompletableFuture<Void>.
     Он имеет доступ к результату CompletableFuture, к которому он прикреплён.*/
    public void tryThenAccept() {
        CompletableFuture.supplyAsync(() -> {
            return "result from some service";
        }).thenAccept(product -> {
            System.out.println("info about some product : " + product);
        });
    }

    public void tryThenRun() {


        CompletableFuture.supplyAsync(() -> {
            int a = 1;
            int b = 2;
            // Выполняем некоторые расчёты
            return null;
        }).thenRun(() -> {
            // Расчёты завершены
        });


    }
}