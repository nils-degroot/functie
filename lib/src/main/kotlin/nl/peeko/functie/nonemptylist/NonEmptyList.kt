@file:Suppress("WildcardImport")

package nl.peeko.functie.nonemptylist

import nl.peeko.functie.maybe.*

/**
 * List that always has at least one value
 * @param T Type of the values in the list
 */
class NonEmptyList<T>(
    /**
     * First value within the list
     */
    val head: T,
    /**
     * Other values in the list
     */
    val tail: Array<out T>
): Collection<T> {
    /**
     * Get a [Iterator] for the [NonEmptyList]
     */
    override fun iterator(): Iterator<T> = toList().iterator()

    /**
     * Get the count of elements in the [NonEmptyList]
     */
    override val size: Int = tail.size + 1

    /**
     * Check if the list contains the element
     */
    override fun contains(element: T): Boolean =
        head == element || tail.contains(element)

    /**
     * Check if the list contains all the passed elements
     */
    override fun containsAll(elements: Collection<T>): Boolean =
        elements.firstOrNull { !contains(it) }
            .toMaybe()
            .map { false }
            .unwrapOr(true)

    /**
     * Override for [List.isEmpty]. Will always return false
     */
    override fun isEmpty(): Boolean = false

    /**
     * Get the nth item of the [NonEmptyList]. Returning an [Maybe] with the value.
     */
    operator fun get(nth: Int): Maybe<T> =
        if (nth == 0)
            Just(head)
        else if (nth - 1 >= tail.size)
            None
        else
            tail[nth - 1].toMaybe()

    /**
     * Turn the [NonEmptyList] into a [List] with all values of the [NonEmptyList]
     */
    fun toList(): List<T> = listOf(head).plus(tail)

    /**
     * Get the first item of the [NonEmptyList]. Returns [head]
     */
    fun first(): T =
        head

    /**
     * Get the last value of the [NonEmptyList]
     */
    fun last(): T =
        tail.lastOrNull()
            .toMaybe()
            .unwrapOr(head)

    /**
     * Map a [NonEmptyList]<[T]> into a [NonEmptyList]<[S]> with help of the passed [transformation] method.
     */
    inline fun <reified S> map(transformation: (T) -> S): NonEmptyList<S> =
        NonEmptyList(
            transformation(head),
            tail.map(transformation)
                .toTypedArray()
        )
}

/**
 * Initializer method for [NonEmptyList]
 */
fun <T> nonEmptyListOf(headValue: T, vararg otherValues: T): NonEmptyList<T> =
    NonEmptyList(headValue, otherValues)
