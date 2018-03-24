package demo

import demo.AjaxAction.{AjaxRequest, AjaxResponse}

private[demo] trait Serializer[T <: AjaxRequest] {
  def serialize(request: T): String
}

private[demo] trait Deserializer[T <: AjaxResponse] {
  def deserialize(responseText: String): T
}

private[demo] object SerializationContext {
  implicit final class SerializationOps[T <: AjaxRequest](request: T)(implicit serializer: Serializer[T]) {
    /** Serialize this instance to String, format depends underlying implementation, which could be JSON or others. */
    def serialize: String = serializer.serialize(request)
  }

  implicit final class DeserializationOps(responseText: String) {
    def deserilize[T <: AjaxResponse](implicit deserializer: Deserializer[T]): T = deserializer.deserialize(responseText)
  }
}