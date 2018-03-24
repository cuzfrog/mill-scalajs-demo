package demo

import scala.concurrent.Future

private[demo] trait Validator[T <: Model]{
  /** Return error msgs if validation failed. */
  def validate(model: T): Future[ValidationAction]
}

private[demo] object ValidationContext {

  implicit final class ValidationOps[T <: Model](model: T)(implicit validator:Validator[T]){
    def validate: Future[ValidationAction] = validator.validate(model)
  }
}
