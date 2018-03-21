package demo

import scalatags.Text.all._

private object IndexPage {
  val skeleton: String =
    html(
      head(
        scalatags.Text.tags2.title("mill-scalajs-demo"),
        link(rel := "stylesheet", href := "assets/css/bulma.css"),
        link(rel := "stylesheet", href := "assets/css/font-awesome.min.css"),
        script(`type` := "text/javascript", src := "assets/vendor.bundle.js")
      ),
      body(
        div(id := "app"),
        script(`type` := "text/javascript", src := "assets/main.bundle.js")
      )
    ).render
}
