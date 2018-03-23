import mill._
import mill.scalalib._
import mill.scalajslib._

trait AbstractModule extends ScalaModule {
  final def scalaVersion = "2.12.4"

  private final def sharedSources = T.sources {
    ammonite.ops.Path("../", millSourcePath) / "shared" / "src"
  }

  override final def sources = T.sources {
    super.sources() ++ sharedSources()
  }

  override final def compileIvyDeps = Agg(
    //ivy"org.scala-lang:scala-reflect:${scalaVersion()}",
    ivy"org.scala-lang:scala-compiler:${scalaVersion()}"
  )

  override def ivyDeps = Agg(
    ivy"com.typesafe.play::play-json::2.6.9",
    ivy"io.suzaku::diode::1.1.3",
    ivy"com.github.julien-truffaut::monocle-core::1.5.0",
    ivy"com.github.julien-truffaut::monocle-macro::1.5.0"
  )
}

object server extends AbstractModule {
  override final def ivyDeps = T {
    super.ivyDeps() ++ Seq(
      ivy"com.typesafe.akka::akka-http:10.1.0",
      ivy"com.typesafe.akka::akka-stream:2.5.11",
      ivy"com.lihaoyi::scalatags:0.6.7"
    )
  }

  object test extends Tests{
    override final def ivyDeps = Agg(ivy"com.lihaoyi::utest::0.6.0")
    override final def testFrameworks = Seq("utest.runner.Framework")
  }
}

object client extends AbstractModule with ScalaJSModule {
  final def scalaJSVersion = "0.6.22"

  override final def ivyDeps = T {
    super.ivyDeps() ++ Seq(
      ivy"org.scala-js::scalajs-dom::0.9.4",
      ivy"com.github.cuzfrog::simple-sri::0.3.0",
      ivy"com.github.cuzfrog::simple-sri-diode::0.3.0"
    )
  }

  object test extends Tests {
    override final def ivyDeps = Agg(ivy"com.github.cuzfrog::sjest::0.2.0")
    override final def testFrameworks = Seq("demo.MyJestTestFramework")
    override final def moduleKind = T { ModuleKind.CommonJSModule }
  }

  override final def moduleKind = T { ModuleKind.CommonJSModule }
}

object shared extends AbstractModule {}