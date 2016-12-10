/*
 * Copyright (c) 2016 Richard Dallaway
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.underscore.numeric

import shapeless._
import shapeless.ops.hlist.IsHCons

object Implicits {

  implicit def deriveIntegral[
    P,           // The Product type, eg Quantity(value: Int) extends AnyVal
    VN <: HList, // Generic representation of P, eg Int :: HNil
    V            // Single value the product, eg Int
  ](implicit
    gen      : Generic.Aux[P, VN],        // Converter from P to/from VN
    ev       : IsHCons.Aux[VN, V, HNil],  // Proof VN is V followed by HNil
    same     : (V :: HNil) =:= VN,        // Proof V :: HNil is the same as VN
    delegate : Integral[V]                // Integral instance for V
   ): Integral[P] =
    new Integral[P] with Ordering[P] {
      private def unbox(p: P): V = gen.to(p).head
      private def box(v: V): P   = gen.from(v :: HNil)
      // Numeric[P] methods:
      def plus(x: P, y: P): P    = box(delegate.plus(unbox(x), unbox(y)))
      def minus(x: P, y: P): P   = box(delegate.minus(unbox(x), unbox(y)))
      def times(x: P, y: P): P   = box(delegate.times(unbox(x), unbox(y)))
      def negate(x: P): P        = box(delegate.negate(unbox(x)))
      def fromInt(x: Int): P     = box(delegate.fromInt(x))
      def toInt(x: P): Int       = delegate.toInt(unbox(x))
      def toLong(x: P): Long     = delegate.toLong(unbox(x))
      def toFloat(x: P): Float   = delegate.toFloat(unbox(x))
      def toDouble(x: P): Double = delegate.toDouble(unbox(x))
      def compare(x: P, y: P)    = delegate.compare(unbox(x), unbox(y))
      // Integral[P] methods:
      def quot(x: P, y: P): P    = box(delegate.quot(unbox(x), unbox(y)))
      def rem(x: P, y: P): P     = box(delegate.rem(unbox(x), unbox(y)))
    }

  implicit def deriveFractional[
    P,
    VN <: HList,
    V
  ](implicit
    gen      : Generic.Aux[P, VN],
    ev       : IsHCons.Aux[VN, V, HNil],
    same     : (V :: HNil) =:= VN,
    delegate : Fractional[V]
   ): Fractional[P] =
    new Fractional[P] with Ordering[P] {
      private def unbox(p: P): V = gen.to(p).head
      private def box(v: V): P   = gen.from(v :: HNil)
      // Numeric[P] methods:
      def plus(x: P, y: P): P    = box(delegate.plus(unbox(x), unbox(y)))
      def minus(x: P, y: P): P   = box(delegate.minus(unbox(x), unbox(y)))
      def times(x: P, y: P): P   = box(delegate.times(unbox(x), unbox(y)))
      def negate(x: P): P        = box(delegate.negate(unbox(x)))
      def fromInt(x: Int): P     = box(delegate.fromInt(x))
      def toInt(x: P): Int       = delegate.toInt(unbox(x))
      def toLong(x: P): Long     = delegate.toLong(unbox(x))
      def toFloat(x: P): Float   = delegate.toFloat(unbox(x))
      def toDouble(x: P): Double = delegate.toDouble(unbox(x))
      def compare(x: P, y: P)    = delegate.compare(unbox(x), unbox(y))
      // Fractional[P] methods:
      def div(x: P, y: P): P     = box(delegate.div(unbox(x), unbox(y)))
    }
}

