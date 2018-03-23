package demo.component

import demo._
import sri.react._
import sri.web.vdom.tagsPrefix_<^._

final class Button extends ComponentP[Button.Props] {
  override def render(): ReactRenders = {
    <.button(
      ^.name := props.name,
      ^.className := props.style.className
    )(props.caption)
  }
}

object Button {
  final case class Props(name: String, caption: String,
                         onClickCallback: ReactEventI => Unit,
                         isLoading: Boolean, style: CssStyle)

  def apply(name: String, caption: String,
            locator: RootModel => ButtonModel, action: UserAction,
            style: CssStyle = CssStyle.empty): ReactElement = {
    AppStore.connect(locator) { proxy =>
      val model = proxy()
      val onClickCallback = (event: ReactEventI) => {
        event.preventDefault()
        println("Login form submitted!")
        proxy.dispatch(action)
      }
      val props = Props(name, caption, onClickCallback, model.isLoading, style)
      CreateElement[Button](props)
    }
  }
}
