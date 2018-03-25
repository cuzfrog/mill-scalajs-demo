package demo.component.input

import demo.action.UserAction
import demo.model.{ClientRootModel, ClientStore}
import sri.react._
import sri.web.vdom.tagsPrefix_<^._

private final class CheckBox extends ComponentP[CheckBox.Props] {
  override def render(): ReactRenders ={
    <.label(^.className := "checkbox")(
      <.input(
        ^.`type` := "checkbox",
        ^.name := props.name,
        ^.onChange := props.onChange
      ),
      props.label
    )
  }
}

private object CheckBox {
  final case class Props(name: String, label: String,
                         isChecked: Boolean, onChange: ReactEventI => Unit)

  def apply(name: String, label: String,
            locator: ClientRootModel => String, action: Boolean => UserAction): ReactElement = {
    ClientStore.connect(locator) { proxy =>
      def isChecked = proxy().toBoolean
      val onChangeCallback = (event: ReactEventI) => {
        proxy.dispatch(action(event.currentTarget.checked))
      }
      val props = Props(name, label, isChecked, onChangeCallback)
      CreateElement[CheckBox](props)
    }
  }
}