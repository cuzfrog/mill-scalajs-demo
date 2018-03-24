package demo.service;

import scala.collection.immutable.Map;
import scala.concurrent.Future;

public interface RequestHandler {
    Future<String> handle(final Map<String, String> header, final String requestData);

    static RequestHandler getInstance(){
        return new RequestHandlerImpl();
    }
}
