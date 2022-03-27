package nl.peeko.functie.maybe

/**
 * Sealed interface that might contain an inner value, if the value is present, implementation of the interface is a
 * [Just]. Otherwise, the value will be [None].
 */
sealed interface Maybe<out T>

/**
 * Type of [Maybe] that is sure to contain a value.
 */
data class Just<T>(
    /**
     * Value contained in the just.
     */
    val value: T
): Maybe<T>

/**
 * Type of [Maybe] that does not have a value.
 */
object None: Maybe<Nothing>

/**
 * Turn a nullable value into a [Maybe]. Returning a [Just] if the value is not null, and returning a [None] otherwise.
 */
fun <T> T?.toMaybe(): Maybe<T> =
    if (this != null)
        Just(this)
    else
        None

/**
 * Attempt to unwrap the [Maybe]. Returning the value contained within the [Just] is it is a [Just].
 * Throws a [NullPointerException] otherwise.
 */
fun <T> Maybe<T>.unwrap(): T =
    when (this) {
        is Just -> value
        None -> throw NullPointerException("Failed to unwrap [Maybe]")
    }

/**
 * Attempt to unwrap the [Maybe]. Returning the value contained within the [Just] is it is a [Just].
 * Returns the value in the default parameter otherwise
 */
fun <T> Maybe<T>.unwrapOr(default: T): T =
    when (this) {
        is Just -> value
        None -> default
    }

/**
 * Maps a [Just]<[T]> into a [Just]<[S]> using the passed method, if the type of the [Maybe] is a [Just].
 * If the inner type is [None], do nothing instead.
 */
inline fun <T, S> Maybe<T>.map(func: (T) -> S): Maybe<S> =
    when (this) {
        is Just -> Just(func(value))
        None -> None
    }

/**
 * Attempts to map a [Just]<[T]> to a [Maybe]<[S]> using the passed method.
 * If the [Maybe] was a [None], do nothing instead.
 */
inline fun <T, S> Maybe<T>.andThen(func: (T) -> Maybe<S>): Maybe<S> =
    when (this) {
        is Just -> func(value)
        None -> None
    }