package demo

import demo.action._

trait Serializer[T <: AjaxRequest] {
  def serialize(request: T): String
}

trait Deserializer[T <: AjaxAction] {
  def deserialize(data: String): T
}

object SerializationContext {
  implicit final class SerializationOps[T <: AjaxRequest](request: T)(implicit serializer: Serializer[T]) {
    /** Serialize this instance to String, format depends underlying implementation, which could be JSON or others. */
    def serialize: String = serializer.serialize(request)
  }

  implicit final class DeserializationOps(data: String) {
    def deserilize[T <: AjaxAction](implicit deserializer: Deserializer[T]): T = deserializer.deserialize(data)
  }
}