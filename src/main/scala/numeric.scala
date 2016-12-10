package io.underscore.numeric

import shapeless._
import shapeless.ops.hlist.IsHCons

object implicits {

  implicit def deriveNumeric[
    P,           // The Product type, eg Quantity(value: Int) extends AnyVal
    VN <: HList, // Generic representation of P, eg Int :: HNil
    V            // Single value the product, eg Int
  ](implicit
    gen    : Generic.Aux[P, VN],        // Converter from P to/from VN
    ev     : IsHCons.Aux[VN, V, HNil],  // Proof VN is V followed by HNil
    same   : (V :: HNil) =:= VN,        // Proof V :: HNil is the same as VN
    numV   : Numeric[V]                 // Numeric instance for V
   ): Numeric[P] =
    new Numeric[P] with Ordering[P] {
      private def unbox(p: P): V = gen.to(p).head
      private def box(v: V): P   = gen.from(v :: HNil)
      def plus(x: P, y: P): P    = box(numV.plus(unbox(x), unbox(y)))
      def minus(x: P, y: P): P   = box(numV.minus(unbox(x), unbox(y)))
      def times(x: P, y: P): P   = box(numV.times(unbox(x), unbox(y)))
      def negate(x: P): P        = box(numV.negate(unbox(x)))
      def fromInt(x: Int): P     = box(numV.fromInt(x))
      def toInt(x: P): Int       = numV.toInt(unbox(x))
      def toLong(x: P): Long     = numV.toLong(unbox(x))
      def toFloat(x: P): Float   = numV.toFloat(unbox(x))
      def toDouble(x: P): Double = numV.toDouble(unbox(x))
      def compare(x: P, y: P)    = numV.compare(unbox(x), unbox(y))
    }
}

