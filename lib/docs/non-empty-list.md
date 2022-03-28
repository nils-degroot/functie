# Package nl.peeko.functie.nonemptylist

A [NonEmptyList] is a list that always contains at least one value
(represented by [NonEmptyList.head]). The remained of the list is an unknown
amount of values ([NonEmptyList.tail]). Because a value always exists in the
list, the [NonEmptyList.first] and [NonEmptyList.last] method cannot return a
null. To get a specific element from the list, [NonEmptyList.get] can be used.

To create a [NonEmptyList], the method [nonEmptyListOf] can be used.