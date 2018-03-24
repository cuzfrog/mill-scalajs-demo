package demo.service;

import demo.model.Credential;
import demo.model.Session;
import scala.Option;
import scala.concurrent.Future;

public interface AuthService {
    Future<Option<Session>> auth(final Credential credential);

    static AuthService getInstance(){
        return new AuthServiceMockImpl();
    }
}
