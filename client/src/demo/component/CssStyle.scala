package demo.component

private final case class CssStyle(className: String = "",
                                  `type`: String = "")

private object CssStyle {
  val empty: CssStyle = CssStyle()

  sealed trait Color{
    override def toString: String = s"is-${this.getClass.getSimpleName.toLowerCase}"
  }
  object Color{
    case object Success extends Color
    case object Danger extends Color
  }
}
