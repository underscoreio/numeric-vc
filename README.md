# Numeric Value Classes

## Summary

Provides instances of `Numeric[T]` for a value class.

## What?

I want to give meaningful types to values.
I use value classes for this.
Sometimes these are numeric, such as:

```scala
case class Quantity(value: Int)
```

For numeric value classes, it is sometimes convenient to be able to do:

```scala
Quantity(1) + Quantity(2)
```

This project enables basic numeric operations on numeric value classes.

## Usage

TODO


## Mission Statement

Provide basic numeric capabilities for value classes.
This is not a units library.



