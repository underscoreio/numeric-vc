package io.underscore.numeric

import org.scalatest._

class Examples extends FlatSpec {

  "Examples" should "demonstrate simple Integer usage" in {

    import io.underscore.numeric.Implicits._
    import Numeric.Implicits._

    case class Count(value: Int)

    assert(
      Count(1) + Count(2) == Count(3)
    )
  }

  it should "demonstrate division" in {

    import io.underscore.numeric.Implicits._
    import Integral.Implicits._ // To use / we need this more specific import

    case class Count(value: Int)

    assert(
      Count(1) / Count(2) == Count(1 / 2)
    )
  }

  it should "demonstrate fractional division" in {

    import io.underscore.numeric.Implicits._
    import Fractional.Implicits._

    case class Count(value: Double)

    assert(
      Count(1d) / Count(2) == Count(1d / 2d)
    )
  }

  it should "demonstrate phantom types" in {

    import io.underscore.numeric.Implicits._
    import Numeric.Implicits._

    case class Count[T](value: Long)
    sealed trait Dog

    assert(
      Count[Dog](1) + Count[Dog](2) == Count[Dog](3)
    )

    // But, mixing cats and dogs is: error: value + is not a member of Count[Dog]
    import shapeless.test.illTyped
    sealed trait Cat
    illTyped {
      "Count[Dog](1) + Count[Cat](1)"
    }
  }

  it should "not permit mixing phantom types" in {

    import io.underscore.numeric.Implicits._
    import Numeric.Implicits._
    import shapeless.test.illTyped

    case class Count[T](value: Long)
    sealed trait Dog
    sealed trait Cat

    // Mixing cats and dogs is: error: value + is not a member of Count[Dog]
    illTyped {
      "Count[Dog](1) + Count[Cat](1)"
    }
  }

}
