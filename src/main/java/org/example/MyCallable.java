package org.example;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {
    private Integer counter = 0;

    public MyCallable(String threadName) {
        this.threadName = threadName;
    }

    private final String threadName;

    @Override
    public Integer call() throws InterruptedException {
        Thread.currentThread().setName(threadName);
        int max = (int) (Math.random() * 5);
        for (int i = 0; i <= max; i++) {
            Thread.sleep(2500);
            counter++;
            System.out.printf("Всем привет из потока %s! (Выводится сообщение: %s/%s)\n", Thread.currentThread().getName(), counter, max + 1);
            }

        System.out.printf("%s завершен\n", threadName);
        return counter;
    }
}
