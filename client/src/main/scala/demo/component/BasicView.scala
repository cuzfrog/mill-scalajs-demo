package demo.component

import sri.react._
import sri.web.vdom.tagsPrefix_<^._

final class BasicView extends ComponentP[BasicView.Props] {
  override def render(): ReactRenders = {
    <.div()(props.value)
  }
}

object BasicView {
  case class Props(value: String = "hello world!")

  def apply(props: Props = Props()): CompositeElement = CreateElement[BasicView](props)
}