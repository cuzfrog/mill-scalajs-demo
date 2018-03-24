package demo.route

import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import demo.IndexPage

private[route] object HomeRoute {
  def apply(): Route = pathSingleSlash {
    get {
      complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, IndexPage.skeleton.render))
    }
  } ~
    pathPrefix("assets" / Remaining) { file =>
      encodeResponse {
        getFromResource(s"assets/$file")
      }
    }
}
