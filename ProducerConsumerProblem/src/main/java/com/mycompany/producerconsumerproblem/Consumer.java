package com.mycompany.producerconsumerproblem;


public class Consumer extends Thread {
    private final Storage storage;

    public Consumer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        while (true) {
            try {
                storage.consume();
                Thread.sleep((long) (Math.random() * 1000)); // Tüketici hızını simüle eder
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
