package romanroe;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public final class Main1 {

    public static void main(String[] args) throws InterruptedException {
//        Observable<Integer> zahlen1 = Observable.just(1, 2, 3);
//        Observable<Integer> zahlen2 = Observable.just(4, 5);
//        Observable<String> zipped = zahlen1.zipWith(zahlen2, (a, b) -> a + "-" + b);

        Observable<String> interval1 = Observable.interval(1, TimeUnit.SECONDS)
                .map(i -> "" + i);
        Observable<Long> interval2 = Observable.interval(500, TimeUnit.MILLISECONDS);

//        interval1.withLatestFrom(interval2, (a, b) -> a + "/" + b)
//                .subscribe(zahl -> {
//                    System.out.println("Wert:" + zahl);
//                });


        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //


//        Observable<Integer> obs1$ = Observable.just(1, 2, 3);
//        Flowable<Integer> just = Flowable.just(1, 2, 3);
//        Subject<String> stringSubject = BehaviorSubject.create();
//        stringSubject.onNext("string");
//
// map, filter,
//        Observable<String> obs2$ = Observable.just("a", "b", "c");
//        obs1$.zipWith(obs2$, (a, b) -> a + "-" + b)
//                .subscribe(System.out::println);


//        Observable<Integer> obs3$ = Observable.create(e -> {
//            for (int i = 0; i < 5; i++) {
//                e.onNext(i);
//            }
//        });

//        Observable<Integer> obs4$ = Observable.create(e -> {
//            for (int i = 0; i < 5; i++) {
//                e.onNext(i);
//            }
//        });

//        obs3$
//                .zipWith(obs4$, (a, b) -> a + "-" + b)
//                .subscribe(System.out::println);


//        Observable<Long> obs5 = Observable.intervalRange(0, 5, 0, 1000, TimeUnit.MILLISECONDS);
//        Observable<Long> obs6 = Observable.intervalRange(100, 5, 3000, 500, TimeUnit.MILLISECONDS);
//        obs5
//                .withLatestFrom(obs6, (a, b) -> a + "-" + b)
//                .subscribe(System.out::println);


//        Observable<Object> obsError = Observable.create(o -> {
//            o.onNext(1);
//            o.onNext(2);
//            o.onError(new RuntimeException("e"));
//            o.onNext(3);
//        });

//        obsError.subscribe(new Observer<Object>() {
//            public void onSubscribe(Disposable d) {
//                System.out.println("onSubscribe");
//            }
//            public void onNext(Object aLong) {
//                System.out.println("aLong = " + aLong);
//            }
//            public void onError(Throwable e) {
//                System.out.println("onError:" + e.toString());
//            }
//            public void onComplete() {
//                System.out.println("onComplete");
//            }
//        });

        Observable<Integer> interval = Observable.<Integer>create(e -> {
            new Thread(() -> {
                System.out.println("Neuer Thread");
                try {
                    e.onNext(1);
                    Thread.sleep(500);
                    e.onNext(2);
                    Thread.sleep(500);
                    e.onComplete();
                } catch (InterruptedException e1) {
                    throw new RuntimeException(e1);
                }
            }).start();
        }).share();

        interval.subscribe(i -> System.out.println("a:" + i));
        interval.subscribe(i -> System.out.println("b:" + i));
        interval.subscribe(i -> System.out.println("c:" + i));
//

//        Flowable.create(subscriber -> {
//            int count = 0;
//            while (true) {
//                subscriber.onNext(count++);
//            }
//        }, BackpressureStrategy.DROP)
//                .observeOn(Schedulers.newThread(), false, 1)
//                .subscribe(val -> {
//                            Thread.sleep(1000);
//                            System.out.println(val);
//                        }
//                );

//        Flowable.create(subscriber -> {
//            int count = 0;
//            while (true) {
//                subscriber.onNext(count++);
//            }
//        }, BackpressureStrategy.MISSING)
//                .onBackpressureBuffer(10)
//                .observeOn(Schedulers.newThread(), false, 1)
//                .subscribe(val -> {
//                            Thread.sleep(1000);
//                            System.out.println(val);
//                        }
//                );


        Thread.sleep(10000);

    }


}
