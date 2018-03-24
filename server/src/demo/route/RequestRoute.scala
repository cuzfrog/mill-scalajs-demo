package demo.route

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import demo.service.RequestHandler

private[route] object RequestRoute {

  private val requestHandler = RequestHandler.getInstance()

  def apply(): Route = path("api" / "request") {
    extractRequest { request =>
      entity(as[String]) { json =>
        val httpHeader = request.headers.map(h => h.name() -> h.value()).toMap
        complete(requestHandler.handle(httpHeader, json))
      }
    }
  }
}
