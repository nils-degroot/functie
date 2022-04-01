package nl.peeko.functie.maybe

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import nl.peeko.functie.outcome.Failure
import nl.peeko.functie.outcome.Ok

internal class MaybeTest: BehaviorSpec({
    Given("Just(2)") {
        val maybe: Maybe<Int> = Just(2)

        When("okOr is called") {
            Then("the result should be Ok(2)") {
                maybe.okOr(Exception()) shouldBe Ok(2)
            }
        }

        When("unwrap is called") {
            Then("the result should be 2") {
                maybe.unwrap() shouldBe 2
            }
        }

        When("unwrapOr(5) is called") {
            Then("the result should be 2") {
                maybe.unwrapOr(5)
            }
        }

        When("map square is called") {
            Then("the result should be Just(4)") {
                maybe.map { it * it } shouldBe Just(4)
            }
        }

        When("andThen Just(square) is called") {
            Then("Just(4) is returned") {
                maybe.andThen { Just(it * it) } shouldBe Just(4)
            }
        }

        When("andThen None is called") {
            Then("None is returned") {
                maybe.andThen { None } shouldBe None
            }
        }
    }

    Given("None") {
        val none: Maybe<Int> = None

        When("okOr is called") {
            Then("Failure is returned") {
                none.okOr(Exception()).shouldBeTypeOf<Failure>()
            }
        }

        When("unwrap is called") {
            Then("A null pointer exception is thrown") {
                shouldThrow<NullPointerException> { none.unwrap() }
            }
        }

        When("unwrapOr(5) is called") {
            Then("5 is returned") {
                none.unwrapOr(5) shouldBe 5
            }
        }

        When("map square is called") {
            Then("None is returned") {
                none.map { it * it } shouldBe None
            }
        }

        When("andThen square is called") {
            Then("None is returned") {
                none.andThen { Just(it * it) } shouldBe None
            }
        }
    }

    Given("nullable 2") {
        When("toMaybe is called") {
            Then("Just 2 is returned") {
                (2 as Int?).toMaybe() shouldBe Just(2)
            }
        }
    }

    Given("null") {
        When("toMaybe is called") {
            Then("None is returned") {
                (null as Int?).toMaybe() shouldBe None
            }
        }
    }
})
