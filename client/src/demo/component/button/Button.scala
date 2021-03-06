package demo.component.button

import demo.action.UserAction
import demo.component.CssStyle
import demo.model.{ClientStore, ButtonModel, ClientRootModel}
import sri.react._
import sri.web.vdom.tagsPrefix_<^._

private final class Button extends ComponentP[Button.Props] {
  override def render(): ReactRenders = {
    <.button(
      ^.name := props.name,
      ^.className := className,
      ^.onClick := props.onClickCallback
    )(props.caption)
  }

  private def className = {
    val isLoading = if(props.isLoading) " is-loading" else ""
    props.style.className + isLoading
  }
}

private object Button {
  final case class Props(name: String, caption: String,
                         onClickCallback: ReactEventI => Unit,
                         isLoading: Boolean, style: CssStyle)

  def apply(name: String, caption: String,
            locator: ClientRootModel => ButtonModel, action: UserAction,
            style: CssStyle = CssStyle.empty): ReactElement = {
    ClientStore.connect(locator) { proxy =>
      def model = proxy()
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
