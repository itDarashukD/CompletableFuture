package completableFututre.example;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Get_Join_Complete {
    public static void main(String[] args) {

        new Get_Join_Complete().createCF();
    }

    public void createCF() {
        try {
            CompletableFuture<String> completableFuture = new CompletableFuture<>();
            String s = completableFuture.get();
            String join = completableFuture.join();

            completableFuture.complete("some result value");

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


    }
}
