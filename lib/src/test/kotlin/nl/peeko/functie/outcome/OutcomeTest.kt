package nl.peeko.functie.outcome

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import java.io.IOException

internal class OutcomeTest: BehaviorSpec({
    Given("Ok(2)") {
        val ok: Outcome<Int> = Ok(2)

        When("unwrap is called") {
            Then("2 is returned") {
                ok.unwrap() shouldBe 2
            }
        }

        When("unwrapOr(5) is called") {
            Then("2 is returned") {
                ok.unwrapOr(5) shouldBe 2
            }
        }

        When("map square is called") {
            Then("Ok(4) is returned") {
                ok.map { it * it } shouldBe Ok(4)
            }
        }

        When("andThen Ok(square) is called") {
            Then("Ok(4) is returned") {
                ok.andThen { Ok(it * it) } shouldBe Ok(4)
            }
        }

        When("andThen Failure is called") {
            Then("a Failure is returned") {
                ok.andThen { Failure(Exception()) }.shouldBeTypeOf<Failure>()
            }
        }
    }

    Given("a Failure") {
        val failure: Outcome<Int> = Failure(Exception())

        When("unwrap is called") {
            Then("an exception is thrown") {
                shouldThrow<Exception> { failure.unwrap() }
            }
        }

        When("unwrapOr(5) is called") {
            Then("5 is returned") {
                failure.unwrapOr(5) shouldBe 5
            }
        }

        When("map square is called") {
            Then("a failure is returned") {
                failure.map { it * it }.shouldBeTypeOf<Failure>()
            }
        }

        When("andThen square is called") {
            Then("a failure is returned") {
                failure.andThen { Ok(it * it) }.shouldBeTypeOf<Failure>()
            }
        }
    }

    Given("nothing") {
        When("attempt 2 is called") {
            Then("Ok(2) is returned") {
                attempt { 2 } shouldBe Ok(2)
            }
        }

        When("a exception is thrown in an attempt") {
            val attempt = attempt { throw IOException() }

            Then("the return type is a failure") {
                attempt.shouldBeTypeOf<Failure>()
            }
            Then("the correct exception is captured") {
                (attempt as Failure).throwable.shouldBeTypeOf<IOException>()
            }
        }
    }
})
