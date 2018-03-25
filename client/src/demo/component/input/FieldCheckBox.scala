package demo.component.input

import demo.action.UserAction
import demo.component.element.{Control, Field}
import demo.model.ClientRootModel
import sri.react.ReactElement

private[component] object FieldCheckBox {
  def apply(name: String, label: String,
            locator: ClientRootModel => String, action: Boolean => UserAction): ReactElement = Field(
    Control()(
      CheckBox(name, label, locator, action)
    )
  )
}
