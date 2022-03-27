# Package nl.peeko.functie.maybe

A [Maybe] represents a nullable value. A nullable value can be turned into a 
maybe with the use of [toMaybe]. Due to the value being a separate type, 
you are able to [Maybe.map] over the value, allowing for more power over the 
type.

The [Maybe] type is seperated into two other types

## [Just]

A [Just] represents a value that exists, the stored value of the [Just] (Just.
value) can be retrieved on multiple ways, for example: using [Maybe.unwrap].

## [None]

A [None] represents a value that is null. Most method (such as [Maybe.map]) 
ignore the value in favor of using the [Just].