package demo

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

package object route {
  def getRoute: Route = Seq(
    HomeRoute(), RequestRoute()
  ).reduce(_ ~ _)
}
