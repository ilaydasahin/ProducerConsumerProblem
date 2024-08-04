package com.mycompany.producerconsumerproblem;
//Bu projede, Java programlama dili ve NetBeans IDE kullanarak bir üretici-tüketici probleminin gelişmiş bir versiyonunu oluşturacağız. 


public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage(10); // Depolama kapasitesi 10
                
        Producer producer1 = new Producer(storage);
        Producer producer2 = new Producer(storage);
        Consumer consumer1 = new Consumer(storage);
        Consumer consumer2 = new Consumer(storage);

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
    }
}
