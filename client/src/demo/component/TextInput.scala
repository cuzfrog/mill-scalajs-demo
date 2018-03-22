package demo.component

import demo.{AppStore, RootModel, UserInput}
import sri.react._
import sri.web.vdom.tagsPrefix_<^._

private final class TextInput extends ComponentP[TextInput.Pros] {
  override def render(): ReactRenders = {
    println(s"TextInput[${props.name}] rendered with value: '${props.value}'")
    <.input(
      ^.value := props.value,
      ^.onChange := props.onChangeCallback
    )
  }
}

private object TextInput {

  final case class Pros(name:String, value: String, onChangeCallback: ReactEventI => Unit, style: String)

  def apply(name: String, locator: RootModel => String, action: String => UserInput, style: String = ""): ReactElement = {
    AppStore.connect(locator) { proxy =>
      val value: String = proxy.apply()
      val onChangeCallback: ReactEventI => Unit = (event: ReactEventI) => {
        val newValue = event.target.value
        println(s"Received input value: '$newValue' | old value: '$value'")
        event.defaultPrevented
        proxy.dispatch(action(newValue))
      }
      CreateElement[TextInput](Pros(name,value, onChangeCallback, style))
    }
  }
}