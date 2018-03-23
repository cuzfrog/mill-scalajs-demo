package demo.validation

import demo.Model

private[demo] object ValidationContext {
  trait Validator[T <: Model]{
    /** Return error msgs if validation failed. */
    def validate(model: T): Seq[String]
  }

  implicit final class ValidationOps[T <: Model](model: T)(implicit validator:Validator[T]){
    def validate: Seq[String] = validator.validate(model)
  }
}
