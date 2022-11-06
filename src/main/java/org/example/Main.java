package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // все задачи
        System.out.println("Вывод сообщений во всех потоках");
        ExecutorService threadPoolAllResults = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Future<Integer>> allResults = threadPoolAllResults.invokeAll(getMyCallableList());
        System.out.println("Выведено сообщений:");
        for (Future<Integer> result : allResults) {
            System.out.println(result.get());
        }
        threadPoolAllResults.shutdown();

        // первый выполнившийся
        System.out.println("Вывод сообщений в первом завершившимся потоке");
        ExecutorService threadPoolAny = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        Integer firstRresult = threadPoolAny.invokeAny(getMyCallableList());
        System.out.println("Выведено сообщений: "+ firstRresult);
        threadPoolAny.shutdown();
    }

    private static ArrayList<Callable<Integer>> getMyCallableList() {
        ArrayList<Callable<Integer>> list = new ArrayList<>();

        Callable<Integer> myCallable1 = new MyCallable("поток 1");
        Callable<Integer> myCallable2 = new MyCallable("поток 2");
        Callable<Integer> myCallable3 = new MyCallable("поток 3");
        Callable<Integer> myCallable4 = new MyCallable("поток 4");

        list.add(myCallable1);
        list.add(myCallable2);
        list.add(myCallable3);
        list.add(myCallable4);

        return list;
    }
}