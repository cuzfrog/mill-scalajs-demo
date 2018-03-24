package demo.service

import demo.model.{Credential, Session}

import scala.concurrent.Future
import scala.util.Random

private final class AuthServiceMockImpl extends AuthService {
  override def auth(credential: Credential): Future[Option[Session]] = {
    val sessionId = {
      val randomLong = Random.nextLong()
      if(randomLong > 0) randomLong else randomLong.unary_-
    }
    Thread.sleep(500)
    Future.successful(Some(Session(sessionId)))
  }
}
