package completableFututre.example;

import java.util.concurrent.CompletableFuture;

/*Если в исходной задаче supplyAsync() возникнет ошибка,
 тогда ни одна из последующих задач thenApply() не будет вызвана и Future завершится с исключением.
  Если ошибка возникнет в первом thenApply(),
 то все последующие задачи в цепочке не будут запущены и Future всё так же завершится с исключением.*/
public class ExceptionsCF {

    public static void main(String[] args) {
//        System.out.println(new ExceptionsCF().tryExceptionally().join());

        System.out.println(new ExceptionsCF().tryHandle().join());
    }

    /*Метод exceptionally() даёт возможность обойти возможные ошибки,
    если они есть. Можно залогировать исключение и вернуть значение по умолчанию.
    что ошибка не будет распространяться далее по цепочке, если вы её обработаете.*/

    public CompletableFuture<String> tryExceptionally() {

        Integer age = -1;

        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
            if (age < 0) {
                throw new IllegalArgumentException("Возраст не может быть отрицательным");
            }
            if (age > 18) {
                return "Взрослый";
            } else {
                return "Ребёнок";
            }
        }).exceptionally(ex -> {
            System.out.println("Ой! У нас тут исключение - " + ex.getMessage());
            return "Неизвестно!";
        });
        return maturityFuture;
    }

    /*Для восстановления после исключений API также предоставляет более общий метод handle().
     Он вызывается независимо от того, возникло исключение или нет.*/
    public CompletableFuture<String> tryHandle() {
        Integer age = -1;

        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
            if (age < 0) {
                throw new IllegalArgumentException("Возраст не может быть отрицательным");
            }
            if (age > 18) {
                return "Взрослый";
            } else {
                return "Ребёнок";
            }
        }).handle((res, ex) -> {
            if (ex != null) {
                System.out.println("Ой! У нас тут исключение - " + ex.getMessage());
                return "Неизвестно!";
            }
            return res;
        });
        return maturityFuture;
    }

}
