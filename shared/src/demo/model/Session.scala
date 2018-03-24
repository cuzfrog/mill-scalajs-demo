package demo.model

final case class Session(sessionId: Long = -1L) extends Model{
  def toHttpHeader: Map[String, String] = ???
}

object Session{
  def fromHttpHeader(header: Map[String, String]): Session = ???
}