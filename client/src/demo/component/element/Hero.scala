package demo.component.element

import sri.react.ReactElement
import sri.web.vdom.tagsPrefix_<^._

private[component] object Hero {
  def apply(title: String, subtitle: String,
            style: String = "is-info"): ReactElement = <.section(^.className := s"hero $style")(
    <.div(^.className := "hero-body")(
      Title(title), SubTitle(subtitle)
    )
  )
}
