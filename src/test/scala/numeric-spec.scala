package io.underscore.numeric

import org.scalacheck._
import Prop.forAll

object NumericSpecification extends Properties("Numeric Derivation") {

  import io.underscore.numeric.implicits._
  import Numeric.Implicits._

  //case class Count[T](get: T)
  //mktest[T] ... Generic[T]... gen.from... etc

  property("Int") = forAll { (a: Int, b: Int) =>
    case class Count(get: Int)
    val ca = Count(a)
    val cb = Count(b)
    ca + cb == Count(a + b) &&
    ca - cb == Count(a - b) &&
    ca * cb == Count(a * b) &&
    (- ca)  == Count(-a)
  }

  property("Double") = forAll { (a: Double, b: Double) =>
    case class Count(get: Double)
    val ca = Count(a)
    val cb = Count(b)
    ca + cb == Count(a + b) &&
    ca - cb == Count(a - b) &&
    ca * cb == Count(a * b) &&
    (- ca)  == Count(-a)
  }

}
