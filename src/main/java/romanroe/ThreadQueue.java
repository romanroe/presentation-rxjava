package romanroe;

import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static romanroe.Utils.cpu;

public final class ThreadQueue {

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger ai = new AtomicInteger(0);

        Subject<Runnable> queue = PublishSubject.create();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        queue
//                .observeOn(Schedulers.single())
                .observeOn(Schedulers.from(executorService))
//                .observeOn(Schedulers.computation())
//                .observeOn(Schedulers.io())
//                .subscribeOn(Schedulers.from(executorService))
//                .subscribeOn(Schedulers.computation())

                .map(c -> {
//                    System.out.println("map:" + Thread.currentThread().toString());
                    c.run();
                    return c;
                })
                .subscribe(c -> {
//                    System.out.println("sub: " + Thread.currentThread().toString());
//                    c.run();
                });

        for (int i = 0; i < 10; i++) {
                queue.onNext(() -> {
                        System.out.println("action:" + Thread.currentThread().toString() + ": " + cpu());
                });
        }


        Thread.sleep(10000);
        System.out.println("Ende");
    }

}
