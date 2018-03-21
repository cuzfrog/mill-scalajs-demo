package demo

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContextExecutor

object ServerMain extends App {
  private implicit val system: ActorSystem = ActorSystem("my-system")
  private implicit val materializer: ActorMaterializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  private implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  val route =
    pathSingleSlash {
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, IndexPage.skeleton))
      }
    }

  val bindingFuture = Http().bindAndHandle(route, "0.0.0.0", 8080)

  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  scala.io.StdIn.readLine() // let it run until user presses return
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate()) // and shutdown when done
}