case class Count(value: Int) extends AnyVal

object Example extends App {

  import Numeric.Implicits._
  import io.underscore.numeric.Implicits._

  val one = Count(1)
  val two = Count(2)

  val result: Count = one + two

  val bert: Int = 1
  val ernie: Int = 2
  val beaker: Int = bert + ernie

  println(result)

}
