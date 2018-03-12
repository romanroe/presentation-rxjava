package romanroe;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

import java.sql.ResultSet;

public final class Jdbc {

    public static Observable<ResultSet> resultSetToObservable(ResultSet resultSet) {
        Subject<ResultSet> s = PublishSubject.create();

        try {
            while (resultSet.next()) {
                s.onNext(resultSet);
            }
        } catch (Exception e) {
            s.onError(e);
        }

        return s;
    }

    public static void main(String[] args) {
        ResultSet resultSet = null;

        Observable<ResultSet> resultSetObservable = resultSetToObservable(resultSet);

        resultSetObservable.subscribe(row -> {
            System.out.println("first column: " + row.getString(1));
        });
    }


}
