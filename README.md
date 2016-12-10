# Numeric Value Classes

## Summary

Provides instances of `Numeric[T]` for value classes.

## What?

I want to give meaningful types to values.
I use value classes for this.
Sometimes these are numeric, such as:

```scala
case class Quantity(value: Int) extends AnyVal
```

For numeric value classes it is sometimes convenient to be able to do:

```scala
Quantity(1) + Quantity(2)
```

This project enables basic numeric operations on numeric value classes.

## Usage

Yeah, so I've not published this yet.
If you want me to, raise an issue.

Otherwise, copy the source file (_numeric.scala_)
and depend on shapeless (see _project/Dependencies.scala_).

Then:

```scala
Welcome to Scala 2.12.0 (Java HotSpot(TM) 64-Bit Server VM, Java 1.8.0_112).
Type in expressions for evaluation. Or try :help.

scala> import io.underscore.numeric.Implicits._
import io.underscore.numeric.Implicits._

scala> import Numeric.Implicits._
import Numeric.Implicits._

scala> case class Count(value: Int) extends AnyVal
defined class Count

scala> Count(1) + Count(41)
res0: Count = Count(42)
```

See _examples.scala_ for more usage.

## Mission Statement

Provide core numeric capabilities for value classes.
This is not a units library and nor is it a maths library.




