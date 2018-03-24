package demo.component.input

import demo.action.UserAction
import demo.component.CssStyle
import demo.model.{ClientStore, ClientRootModel}
import sri.react._
import sri.web.vdom.tagsPrefix_<^._

private final class TextInput extends ComponentP[TextInput.Pros] {
  override def render(): ReactRenders = {
    <.input(
      ^.name := props.name,
      ^.className := props.style.className,
      ^.`type` := props.style.`type`,
      ^.placeholder := props.placeholder,
      ^.value := props.value,
      ^.onChange := props.onChangeCallback
    )
  }
}

private object TextInput {

  final case class Pros(name: String, placeholder: String, value: String, onChangeCallback: ReactEventI => Unit, style: CssStyle)

  def apply(name: String, placeholder: String, locator: ClientRootModel => String,
            action: String => UserAction, style: CssStyle = CssStyle.empty): ReactElement = {
    ClientStore.connect(locator) { proxy =>
      def value: String = proxy.apply()
      val onChangeCallback: ReactEventI => Unit = (event: ReactEventI) => {
        val newValue = event.target.value
        event.defaultPrevented
        proxy.dispatch(action(newValue))
      }
      CreateElement[TextInput](Pros(name, placeholder, value, onChangeCallback, style))
    }
  }
}