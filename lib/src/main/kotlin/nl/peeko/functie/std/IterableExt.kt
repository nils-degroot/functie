package nl.peeko.functie.std

import nl.peeko.functie.maybe.Maybe
import nl.peeko.functie.maybe.Just
import nl.peeko.functie.maybe.None
import nl.peeko.functie.maybe.toMaybe
import kotlin.collections.Iterable

/**
 * Extension methods for [Iterable]
 */
object IterableExt {
    /**
     * Returns [Just] with an element at the given [index] or [None] if the index is out of bounds of this iterable.
     */
    fun <T> Iterable<T>.elementAtMaybe(index: Int): Maybe<T> =
        elementAtOrNull(index).toMaybe()

    /**
     * Returns a [Just] with the first element that matches the [predicate], or [None] if the element was not found.
     */
    fun <T> Iterable<T>.findMaybe(predicate: (T) -> Boolean): Maybe<T> =
        find(predicate).toMaybe()

    /**
     * Returns the last element matching the [predicate] wrapped in a [Just], or [None] is the no elements match the
     * [predicate].
     */
    fun <T> Iterable<T>.findLastMaybe(predicate: (T) -> Boolean): Maybe<T> =
        findLast(predicate).toMaybe()

    /**
     * Returns a [Just] with the first element in the iterable, or return a [None] is no elements exist in the
     * [Iterable].
     */
    fun <T> Iterable<T>.firstMaybe(): Maybe<T> =
        firstOrNull().toMaybe()

    /**
     * Returns a [Just] with the first element to match the [predicate], or returns [None] if no elements matches.
     */
    fun <T> Iterable<T>.firstMaybe(predicate: (T) -> Boolean): Maybe<T> =
        firstOrNull(predicate).toMaybe()

    /**
     * Return a [Just] with the last element in the [Iterable], or returns a [None] if no elements are in the
     * [Iterable].
     */
    fun <T> Iterable<T>.lastMaybe(): Maybe<T> =
        lastOrNull().toMaybe()

    /**
     * Return a [Just] with the last element to match the [predicate], or returns a [None] if no elements match.
     */
    fun <T> Iterable<T>.lastMaybe(predicate: (T) -> Boolean): Maybe<T> =
        lastOrNull(predicate).toMaybe()
}
