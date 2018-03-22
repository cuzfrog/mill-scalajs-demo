package demo.component

import sri.react._
import sri.web.vdom.tagsPrefix_<^._

final class GreetingView extends ComponentP[GreetingView.Props] {
  override def render(): ReactRenders = {
    <.div()(props.value)
  }
}

object GreetingView {
  case class Props(value: String = "Greetings!")

  def apply(props: Props = Props()): CompositeElement = CreateElement[GreetingView](props)
}