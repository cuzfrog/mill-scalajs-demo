package demo.component.input

import demo.action.UserAction
import demo.component.CssStyle
import demo.component.element._
import demo.model.RootModel
import sri.react.ReactElement

private[component] object EmailTextInput {
  def apply(name: String, locator: RootModel => String,
            action: String => UserAction): ReactElement =
    Field(
      Control("has-icons-left")(
        TextInput(name, "Email", locator, action, CssStyle("input", "email")),
        Icon("is-small is-left")("fa-envelope")
      )
    )
}
