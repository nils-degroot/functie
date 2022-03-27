package nl.peeko.functie.outcome

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class OutcomeTest: StringSpec({
    // unwrap
    "Given Ok(2) - When unwrap is called - Then 2 is returned" {
        Ok(2).unwrap() shouldBe 2
    }
    "Given Failure(Exception) - When unwrap is called - Then the exception is thrown" {
        shouldThrow<Exception> { (Failure(Exception()) as Outcome<Int>).unwrap() }
    }

    // unwrapOr
    "Given Ok(2) - When unwrapOr(5) is called - Then 2 is returned" {
        Ok(2).unwrapOr(5) shouldBe 2
    }
    "Given Failure(Exception) - When unwrapOr(5) is called - Then 5 is returned" {
        Failure(Exception()).unwrapOr(5) shouldBe 5
    }

    // map
    "Given Ok(2) - When map { it * it } is called - Then Ok(4) is returned" {
        Ok(2).map { it * it } shouldBe Ok(4)
    }
    "Given Failure(Exception) - When { it * it } is called - Then Failure(Exception) is returned" {
        val failure = Failure(Exception()) as Outcome<Int>
        failure.map { it * it } shouldBe failure
    }

    // andThen
    "Given Ok(2) - When andThen { Ok(it * it) } is called - Then Ok(4) is returned" {
        Ok(2).andThen { Ok(it * it) } shouldBe Ok(4)
    }
    "Given Ok(2) - When andThen { Failure(Exception) } is called - Then Failure(Exception) is returned" {
        val failure = Failure(Exception()) as Outcome<Int>
        Ok(2).andThen { failure } shouldBe failure
    }
    "Given Failure(Exception) - When andThen { it * it } is called - Then Failure(Exception) is returned" {
        val failure = Failure(Exception()) as Outcome<Int>
        failure.andThen { Ok(it * it) } shouldBe failure
    }
})
