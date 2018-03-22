package demo.component

final case class CssStyle(className: String)

object CssStyle{
  val empty: CssStyle = CssStyle("")
}
