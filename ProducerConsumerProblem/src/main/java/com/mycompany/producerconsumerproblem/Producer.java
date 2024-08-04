//Üretici sınıfı (`Producer`)
package com.mycompany.producerconsumerproblem;

public class Producer extends Thread {
    private final Storage storage;

    public Producer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int id = 0;
        while (true) {
            try {
                Product product = new Product(id++);
                storage.produce(product);
                Thread.sleep((long) (Math.random() * 1000)); // Üretici hızını simüle eder
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
