package romanroe;

import io.reactivex.Observable;

import java.util.LinkedList;
import java.util.List;

public final class Utils {

    public static Observable<String> createObservableReturn3ValuesIn3Seconds(String input) {
        return Observable.create(observer -> {
            (new Thread(() -> {
                for (long i = 0; i < 3; i++) {
                    observer.onNext(i + ": " + input);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        observer.onError(e);
                    }
                }
                observer.onComplete();
            })).start();
            ;
        });
    }

    public static long cpu() {
        long val = 0;
        long start = System.currentTimeMillis();
        List<Long> list = new LinkedList<>();
        for (int i = 0; i < 3 * 1000 * 1000; i++) {
            list.add(val--);
        }
        list.sort((a, b) -> (int) (a - b));
        long dur = System.currentTimeMillis() - start;
        return dur;
    }

}
