package demo.component.element

import sri.react.ReactElement
import sri.web.vdom.tagsPrefix_<^._

private[component] object Section {
  def apply(children: ReactElement*): ReactElement = <.section(^.className := "section")(children: _*)
}

private[component] object Container {
  def apply(children: ReactElement*): ReactElement = <.div(^.className := "container")(children: _*)
}

private[component] object Content{
  def apply(children: ReactElement*): ReactElement = <.div(^.className := "content has-text-centered")(children: _*)
}

private[component] object Title {
  def apply(text: String): ReactElement = <.h1(^.className := "title")(text)
}

private[component] object SubTitle {
  def apply(text: String): ReactElement = <.h2(^.className := "subtitle")(text)
}

private[component] object Field {
  def apply(children: ReactElement*): ReactElement = <.div(^.className := "field")(children: _*)
}

private[component] object FieldGroup {
  def apply(children: ReactElement*): ReactElement = <.div(^.className := "field is-grouped")(children: _*)
}

private[component] object Control {
  def apply(style: String = "")(children: ReactElement*): ReactElement = <.p(^.className := s"control $style")(children: _*)
}

private[component] object Icon {
  def apply(style: String = "")(icon: String): ReactElement = <.span(^.className := s"icon $style")(
    <.i(^.className := s"fas $icon")()
  )
}