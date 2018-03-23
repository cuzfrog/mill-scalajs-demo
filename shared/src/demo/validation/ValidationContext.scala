package demo.validation

import demo.Model

private[demo] object ValidationContext {
  trait Validator[T <: Model]{
    /** Return an error msg if validation failed. */
    def validate(model: T): Option[String]
  }

  implicit final class ValidationOps[T <: Model](model: T)(implicit validator:Validator[T]){
    def validate: Option[String] = validator.validate(model)
  }
}
