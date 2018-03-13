package romanroe;

public final class ObservableServletConsumer {

    public static void main(String[] args) {

        ObservableServlet.REQUESTS
                .filter(rr -> rr.req.getPathInfo().startsWith("/route"))
                .subscribe(rr -> {
                    String key1 = rr.req.getParameter("key1");
                    rr.res.onNext("<h1>" + key1 + "</h1>");
                    rr.res.onComplete();
                });
    }

}
