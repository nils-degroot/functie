package nl.peeko.functie.outcome

/**
 * Sealed interface representing the result of a function, can either be a [Ok] or a [Failure].
 */
sealed interface Outcome<out T>

/**
 * Variant of [Outcome] representing that the expression went ok.
 */
data class Ok<T>(
    /**
     * Value contained in the [Ok]
     */
    val value: T
): Outcome<T>

/**
 * Variant of [Outcome] representing that something went wrong.
 */
data class Failure(
    /**
     * [Throwable] contained in the [Failure]
     */
    val throwable: Throwable
): Outcome<Nothing>

/**
 * Attempt to unwrap the [Outcome]. Returning the value if the variant is [Ok].
 * Throw the [Throwable] is the variant is a [Failure].
 */
fun <T> Outcome<T>.unwrap(): T =
    when(this) {
        is Failure -> throw throwable
        is Ok -> value
    }

/**
 * Attempt to unwrap the [Outcome]. Returning the value if the variant is [Ok].
 * Return the default when the value is a [Failure].
 */
fun <T> Outcome<T>.unwrapOr(default: T): T =
    when (this) {
        is Failure -> default
        is Ok -> value
    }

/**
 * Maps a [Ok]<[T]> into a [Ok]<[S]> using the passed method, if the type of the [Outcome] is a [Ok].
 * If the inner type is [Failure], do nothing instead.
 */
inline fun <T, S> Outcome<T>.map(func: (T) -> S): Outcome<S> =
    when (this) {
        is Ok -> Ok(func(value))
        is Failure -> Failure(throwable)
    }

/**
 * Attempts to map a [Ok]<[T]> to a [Outcome]<[S]> using the passed method.
 * If the [Outcome] was a [Failure], do nothing instead.
 */
inline fun <T, S> Outcome<T>.andThen(func: (T) -> Outcome<S>): Outcome<S> =
    when (this) {
        is Ok -> func(value)
        is Failure -> Failure(throwable)
    }
