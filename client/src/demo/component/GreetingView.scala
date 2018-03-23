package demo.component

import demo.AppStore
import sri.react._
import sri.web.vdom.tagsPrefix_<^._

final class GreetingView extends ComponentP[GreetingView.Props] {
  override def render(): ReactRenders = {
    <.div()(props.value)
  }
}

object GreetingView {

  final case class Props(value: String = "Greetings!")

  def apply(): ReactElement = {
    AppStore.connect(_.greetingWords) { proxy =>
      CreateElement[GreetingView](Props(proxy()))
    }
  }
}