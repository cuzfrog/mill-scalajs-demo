package demo.component

private final case class CssStyle(className: String = "",
                                  `type`: String = "")

private object CssStyle {
  val empty: CssStyle = CssStyle()
}
