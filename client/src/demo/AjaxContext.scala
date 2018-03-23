package demo

object AjaxContext {

  trait AjaxSender[T <: Model]

  implicit final class AjaxOps[T <: Model](model: T)(implicit ajaxSender: AjaxSender[T]){

  }

}
