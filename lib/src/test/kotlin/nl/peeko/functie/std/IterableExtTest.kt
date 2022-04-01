package nl.peeko.functie.std

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import nl.peeko.functie.maybe.Just
import nl.peeko.functie.maybe.None
import nl.peeko.functie.std.IterableExt.findLastMaybe

class IterableExtTest: BehaviorSpec({
    Given("a listOf(1, 2, 3)") {
        val list = listOf(1, 2, 3)

        When("findLastMaybe looks for 1") {
            val result = list.findLastMaybe { it == 1 }

            Then("the result should be just 1") {
                result shouldBe Just(1)
            }
        }
    }

    Given("a empty list") {
        val list = listOf<Int>()

        When("findLastMaybe looks for one") {
            val result = list.findLastMaybe { it == 1 }

            Then("the result should be None") {
                result shouldBe None
            }
        }
    }
})
