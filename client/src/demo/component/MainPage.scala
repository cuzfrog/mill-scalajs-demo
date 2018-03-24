package demo.component

import demo.component.element.{Container, Footer, Hero, Section}
import demo.component.module.LoginForm
import sri.react._
import sri.web.vdom.tagsPrefix_<^._

final class MainPage extends ComponentP[MainPage.Props] {
  override def render(): ReactRenders = <.div(^.id := "page-wrapper")(
    Hero(title = "Scala.js", subtitle = "Js/Jvm cross compilation demonstration"),
    PageContent(
      LoginForm()
    ),
    Footer()
  )

  private def PageContent(children: ReactElement) = <.div(^.id := "page-content-wrapper")(
    Section(Container(children))
  )
}

object MainPage {
  final case class Props()

  def apply(): ReactElement = CreateElement[MainPage](Props())
}
