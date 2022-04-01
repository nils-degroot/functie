package nl.peeko.functie.nonemptylist

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import nl.peeko.functie.maybe.Just
import nl.peeko.functie.maybe.None

internal class NonEmptyListTest: BehaviorSpec({
    Given("noneEmptyListOf(1, 2, 3)") {
        val list = nonEmptyListOf(1, 2, 3)

        When("get(0) is called") {
            Then("Just(1) is returned") {
                list[0] shouldBe Just(1)
            }
        }

        When("get(1) is called") {
            Then("Just(2) is returned") {
                list[1] shouldBe Just(2)
            }
        }

        When("get(3) is called") {
            Then("None is returned") {
                list[3] shouldBe None
            }
        }

        When("toList is called") {
            Then("listOf(1, 2, 3) is returned") {
                list.toList() shouldBe listOf(1, 2, 3)
            }
        }

        When("first is called") {
            Then("1 is returned") {
                list.first() shouldBe 1
            }
        }

        When("last is called") {
            Then("3 is returned") {
                list.last() shouldBe 3
            }
        }

        When("contains 1 is called") {
            Then("true is returned") {
                list.contains(1) shouldBe true
            }
        }

        When("contains 2 is called") {
            Then("true is returned") {
                list.contains(2) shouldBe true
            }
        }

        When("contains 4 is called") {
            Then("false is returned") {
                list.contains(4) shouldBe false
            }
        }

        When("containsAll(1, 2, 3) is called") {
            Then("true is returned") {
                list.containsAll(listOf(1, 2, 3)) shouldBe true
            }
        }

        When("containsAll(1, 2, 3, 4) is called") {
            Then("false is returned") {
                list.containsAll(listOf(1, 2, 3, 4)) shouldBe false
            }
        }

        When("map square is called") {
            Then("head is 1 and tail is listOf(4, 9)") {
                list.map { it * it }.apply {
                    head shouldBe 1
                    tail shouldBe listOf(4, 9)
                }
            }
        }
    }

    Given("nonEmptyListOf(1)") {
        val list = nonEmptyListOf(1)

        When("last is called") {
            Then("1 is returned") {
                list.last() shouldBe 1
            }
        }
    }
})
