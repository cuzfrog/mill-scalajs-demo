package demo.utils

private[demo] object delay {
  def apply[T](ms: Double)(block:() => T): Unit =
    org.scalajs.dom.window.setTimeout(block, ms)
}
