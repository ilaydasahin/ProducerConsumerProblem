//Depolama sınıfı (`Storage`)
//senkronizasyon mekanizmaları kullanarak bir shared resource oluşturacağız.
package com.mycompany.producerconsumerproblem;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Storage {
    private final int capacity;
    private final Queue<Product> queue = new LinkedList<>();
    private final Semaphore mutex = new Semaphore(1);
    private final Semaphore empty;
    private final Semaphore full = new Semaphore(0);

    public Storage(int capacity) {
        this.capacity = capacity;
        this.empty = new Semaphore(capacity);
    }

    public void produce(Product product) throws InterruptedException {
        empty.acquire();
        mutex.acquire();

        queue.add(product);
        System.out.println(Thread.currentThread().getName() + " produced product: " + product);

        mutex.release();
        full.release();
    }

    public Product consume() throws InterruptedException {
        full.acquire();
        mutex.acquire();

        Product product = queue.poll();
        System.out.println(Thread.currentThread().getName() + " consumed product: " + product);

        mutex.release();
        empty.release();

        return product;
    }
}
/*### Açıklamalar ve İpuçları

1. **Storage Sınıfı**: Bu sınıfta kullanılan üç tür Semaphore bulunur:
- `mutex`: Bu semafor, sadece bir thread'in kritik bölgeye girmesine izin verir.
- `empty`: Bu semafor, depolama alanında boş yer olup olmadığını kontrol eder.
- `full`: Bu semafor, depolama alanında ürün olup olmadığını kontrol eder.

2. **Producer ve Consumer Sınıfları**: Bu sınıflarda her bir `Producer` ve `Consumer` thread'i, `Storage` nesnesini kullanarak sırasıyla ürün üretecek ve tüketecek.

3. **Main Sınıfı**: Bu sınıfta, depolama alanı, üreticiler ve tüketiciler oluşturulur ve thread'ler başlatılır.*/

/*
Bu çıktı, üreticilerin sürekli olarak ürün ürettiğini ve tüketicilerin de bu ürünleri tükettiğini gösterir. 
Her üretici ve tüketici kendi hızında çalışır, bu hızlar da rastgele olarak belirlenir (0 ile 1 saniye arasında uyuma süreleri ile simüle edilir).


*/