package demo.component.button

import demo.action.UserAction
import demo.component.CssStyle
import demo.component.element.Control
import demo.model.{ButtonModel, ClientRootModel}
import sri.react.ReactElement


private[component] object PrimaryButton {
  def apply(name: String, caption: String,
            locator: ClientRootModel => ButtonModel, action: UserAction): ReactElement = Control()(
    Button(name, caption, locator, action, CssStyle("button is-primary"))
  )
}
