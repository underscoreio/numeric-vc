package io.underscore.numeric

import org.scalacheck._
import Prop.{forAll, BooleanOperators}

object NumericSpecification extends Properties("Numeric Derivation") {

  import io.underscore.numeric.Implicits._

  //case class Count[T](get: T)
  //mktest[T] ... Generic[T]... gen.from... etc

  property("Int Numeric") = forAll { (a: Int, b: Int) =>
    import Numeric.Implicits._
    case class Count(get: Int)
    val ca = Count(a)
    val cb = Count(b)
    ca + cb == Count(a + b) &&
    ca - cb == Count(a - b) &&
    ca * cb == Count(a * b) &&
    (- ca)  == Count(-a)
  }

  property("Int Integral") = forAll { (a: Int, b: Int) =>
    (b != 0) ==> {
      import Integral.Implicits._
      case class Count(get: Int)
      Count(a) / Count(b) == Count(a / b)
    }
  }

  property("Double Numeric") = forAll { (a: Double, b: Double) =>
    import Numeric.Implicits._
    case class Count(get: Double)
    val ca = Count(a)
    val cb = Count(b)
    ca + cb == Count(a + b) &&
    ca - cb == Count(a - b) &&
    ca * cb == Count(a * b) &&
    (- ca)  == Count(-a)
  }

  property("Double Fractional") = forAll { (a: Double, b: Double) =>
    (b != 0d) ==> {
      import Fractional.Implicits._
      case class Count(get: Double)
      Count(a) / Count(b) == Count(a / b)
    }
  }

}
