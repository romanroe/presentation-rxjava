package romanroe;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class DispatcherServlet extends HttpServlet {

    public static final Subject<RequestResponse> REQUESTS = PublishSubject.create();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        RequestResponse rr = new RequestResponse(req);

        rr.res.subscribe(new Observer<String>() {
                             @Override
                             public void onSubscribe(Disposable d) {

                             }

                             @Override
                             public void onNext(String s) {
                                 try {
                                     resp.getWriter().write(s);
                                 } catch (IOException e) {
                                     throw new RuntimeException(e);
                                 }
                             }

                             @Override
                             public void onError(Throwable e) {
                                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                             }

                             @Override
                             public void onComplete() {
                                 try {
                                     resp.getWriter().close();
                                 } catch (IOException e) {
                                     throw new RuntimeException(e);
                                 }
                             }
                         });

        REQUESTS.onNext(rr);
    }

    public static class RequestResponse {

        public final HttpServletRequest req;
        public final Subject<String> res = PublishSubject.create();

        public RequestResponse(HttpServletRequest req) {
            this.req = req;
        }
    }
}
