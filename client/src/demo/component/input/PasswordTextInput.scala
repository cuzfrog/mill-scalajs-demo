package demo.component.input

import demo.{RootModel, UserAction}
import demo.component.CssStyle
import demo.component.element.{Control, Field, Icon}
import sri.react.ReactElement

private[component] object PasswordTextInput {
  def apply(name: String, locator: RootModel => String,
            action: String => UserAction): ReactElement =
    Field(
      Control("has-icons-left")(
        TextInput(name, "Password", locator, action, CssStyle("input", "password")),
        Icon("is-small is-left")("fa-lock")
      )
    )
}
