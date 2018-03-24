package demo

import demo.action.ValidationAction
import demo.model.Model

import scala.concurrent.Future

trait Validator[T <: Model]{
  /** Return error msgs if validation failed. */
  def validate(model: T): Future[ValidationAction]
}

object ValidationContext {

  implicit final class ValidationOps[T <: Model](model: T)(implicit validator:Validator[T]){
    def validate: Future[ValidationAction] = validator.validate(model)
  }
}
