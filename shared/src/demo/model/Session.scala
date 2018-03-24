package demo.model

import play.api.libs.json.{Json, Reads, Writes}

final case class Session(sessionId: Long = -1L) extends Model{
  def toHttpHeader: Map[String, String] = Map(
    "session-id" -> sessionId.toString
  )
}

object Session{
  def fromHttpHeader(header: Map[String, String]): Session = {
    val sessionId = header.get("session-id").map(_.toLong).getOrElse(-1L)
    Session(sessionId)
  }

  implicit val reads: Reads[Session] = Json.reads[Session]
  implicit val writes: Writes[Session] = Json.writes[Session]
}