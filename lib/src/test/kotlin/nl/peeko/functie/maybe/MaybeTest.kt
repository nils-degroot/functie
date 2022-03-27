package nl.peeko.functie.maybe

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import nl.peeko.functie.outcome.Failure
import nl.peeko.functie.outcome.Ok

internal class MaybeTest: StringSpec({
    // toMaybe
    "Given 2? - When toMaybe is called - Then a Just(2) is returned" {
        (2 as Int?).toMaybe() shouldBe Just(2)
    }
    "Given null - When toMaybe is called - Then None is returned" {
        (null as Int?).toMaybe() shouldBe None
    }

    // okOr
    "Given Just(2) - When okOr(Exception) is called - Then Ok(2) is returned" {
        Just(2).okOr(Exception()) shouldBe Ok(2)
    }
    "Given None - When okOr(Exception) is called - Then Failure(Exception) is returned" {
        val exception = Exception()
        None.okOr(exception) shouldBe Failure(exception)
    }

    // unwrap
    "Given Just(2) - When unwrap is called - Then the return value is 2" {
        Just(2).unwrap() shouldBe 2
    }
    "Given None - When unwrap is called - Then a NullPointerException is thrown" {
        shouldThrow<NullPointerException> {
            (None as Maybe<Int>).unwrap()
        }
    }

    // unwrapOr
    "Given Just(2) - When unwrapOr(5) is called - Then 2 is returned" {
        Just(2).unwrapOr(5) shouldBe 2
    }
    "Given None - When unwrapOr(5) is called - Then 5 is returned" {
        None.unwrapOr(5) shouldBe 5
    }

    // map
    "Given Just(2) - When map { it * it } is called - Then Just(4) is returned" {
        Just(2).map { it * it } shouldBe Just(4)
    }
    "Given None - When map { it * it } is called - Then None is returned" {
        (None as Maybe<Int>).map { it * it } shouldBe None
    }

    // andThen
    "Given Just(2) - When andThen { Just(it * it) } is called - Then Just(4) is returned" {
        Just(2).andThen { Just(it * it) } shouldBe Just(4)
    }
    "Given Just(2) - When andThen { None } is called - Then None is returned" {
        Just(2).andThen { None } shouldBe None
    }
    "Given None - When andThen { Just(it * it) } is called - Then None is returned" {
        (None as Maybe<Int>).andThen { Just(it * it) } shouldBe None
    }
})
