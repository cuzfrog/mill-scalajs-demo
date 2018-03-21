import mill._
import mill.scalalib._
import mill.scalajslib._

trait CommonConfig extends SbtModule{
  def scalaVersion = "2.12.4"

  def sharedSources = T.sources{
    ammonite.ops.Path("../", millSourcePath) / "shared" / "src" / "main" / "scala"
  }

  def sources = T.sources{super.sources() ++ sharedSources()}

  def compileIvyDeps = Agg(
    //ivy"org.scala-lang:scala-reflect:${scalaVersion()}",
    ivy"org.scala-lang:scala-compiler:${scalaVersion()}"
  )

  def ivyDeps = Agg(
    ivy"com.typesafe.play::play-json::2.6.9",
    ivy"io.suzaku::diode::1.1.3"
  )

  def platformSegment: String
}

object server extends CommonConfig {
  def platformSegment = "server"

  def ivyDeps = T {
    super.ivyDeps() ++ Seq(
      ivy"com.typesafe.akka::akka-http:10.1.0",
      ivy"com.typesafe.akka::akka-stream:2.5.11",
      ivy"com.lihaoyi::scalatags:0.6.7"
    )
  }
}

object client extends CommonConfig with ScalaJSModule {
  def platformSegment = "client"
  def scalaJSVersion = "0.6.22"

  def ivyDeps = T {
    super.ivyDeps() ++ Seq(
      ivy"org.scala-js::scalajs-dom::0.9.4",
      ivy"com.github.cuzfrog::simple-sri::0.3.0",
      ivy"com.github.cuzfrog::simple-sri-diode::0.3.0"
    )
  }
}