package demo

trait Message extends Product with Serializable{
  def userId: Long
}

final case class TextMessage(userId: Long, text: String) extends Message

