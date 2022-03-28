package nl.peeko.functie.nonemptylist

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import nl.peeko.functie.maybe.Just
import nl.peeko.functie.maybe.None

internal class NonEmptyListTest : StringSpec({
    // nonEmptyListOf
    "Given nothing - When noneEmptyListOf(1, 2, 3) is called - Then a NonEmptyList of size 3 is created" {
        nonEmptyListOf(1, 2, 3).size shouldBe 3
    }

    // iterator
    "Given NonEmptyList(1, 2, 3) - When iterator is called - Then an iterator with 3 elements is returned" {
        var count = 0
        nonEmptyListOf(1, 2, 3).iterator().forEachRemaining { _ -> count++ }
        count shouldBe 3
    }

    // get
    "Given NonEmptyList(1, 2, 3) - When get(0) is called - Then Just(1) is returned" {
        nonEmptyListOf(1, 2, 3)[0] shouldBe Just(1)
    }
    "Given NonEmptyList(1, 2, 3) - When get(1) is called - Then Just(2) is returned" {
        nonEmptyListOf(1, 2, 3)[1] shouldBe Just(2)
    }
    "Given NonEmptyList(1, 2, 3) - When get(3) is called - Then None is returned" {
        nonEmptyListOf(1, 2, 3)[3] shouldBe None
    }

    // toList
    "Given NoneEmptyList(1, 2, 3) - When toList is called - Then a list with 3 elements is returned" {
        nonEmptyListOf(1, 2, 3).toList() shouldBe listOf(1, 2, 3)
    }

    // head
    "Given NoneEmptyList(1, 2, 3) - When head is called - Then 1 is returned" {
        nonEmptyListOf(1, 2, 3).head shouldBe 1
    }
    "Given NoneEmptyList(1, 2, 3) - When first is called - Then 1 is returned" {
        nonEmptyListOf(1, 2, 3).first() shouldBe 1
    }

    // Last
    "Given NonEmptyList(1, 2, 3) - When last is called - Then 3 is returned" {
        nonEmptyListOf(1, 2, 3).last() shouldBe 3
    }
    "Given NonEmptyList(1) - When last is called - Then 1 is returned" {
        nonEmptyListOf(1).last() shouldBe 1
    }

    // tail
    "Given NoneEmptyList(1, 2, 3) - When tail is called - Then listOf(2, 3) is returned" {
        nonEmptyListOf(1, 2, 3).tail shouldBe listOf(2, 3)
    }

    // isEmpty
    "Given NoneEmptyList(1, 2, 3) - When isEmpty is called - Then false is returned" {
        nonEmptyListOf(1, 2, 3).isEmpty() shouldBe false
    }

    // contains
    "Given NoneEmptyList(1, 2, 3) - When contains(1) is called - Then true is returned" {
        nonEmptyListOf(1, 2, 3).contains(1) shouldBe true
    }
    "Given NoneEmptyList(1, 2, 3) - When contains(2) is called - Then true is returned" {
        nonEmptyListOf(1, 2, 3).contains(2) shouldBe true
    }
    "Given NoneEmptyList(1, 2, 3) - When contains(4) is called - Then false is returned" {
        nonEmptyListOf(1, 2, 3).contains(4) shouldBe false
    }

    // containsAll
    "Given NoneEmptyList(1, 2, 3) - When containsAll(1, 2, 3) is called - Then true is returned" {
        nonEmptyListOf(1, 2, 3).containsAll(listOf(1, 2, 3)) shouldBe true
    }
    "Given NoneEmptyList(1, 2, 3) - When containsAll(1, 2, 3, 4) is called - Then false is returned" {
        nonEmptyListOf(1, 2, 3).containsAll(listOf(1, 2, 3, 4)) shouldBe false
    }

    // map
    "Given NoneEmptyList(1, 2, 3) - When map { it * it } is called - Then head is 1 and tail is arrayOf(4, 9)" {
        val list = nonEmptyListOf(1, 2, 3).map { it * it }

        list.head shouldBe 1
        list.tail shouldBe arrayOf(4, 9)
    }
})