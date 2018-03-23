package demo.component.element

import sri.react.ReactElement
import sri.web.vdom.tagsPrefix_<^._

private[component] object Footer {
  def apply(): ReactElement = <.footer(^.className := "footer")(
    Container(
      Content(
        <.p()(
          "This demo is written by Cause Chung. Source code license is ",
          <.a(^.href := "https://www.apache.org/licenses/LICENSE-2.0")("Apache-2.0")
        )
      )
    )
  )
}
