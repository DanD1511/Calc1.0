package com.dand0129.calc10

class Functions {

    fun add(numbers: List<Int>): Int {
        // Extract numbers from the list

        val numberOne = numbers[0]
        val numberTwo = numbers[1]

        return numberOne + numberTwo
    }

    fun subtraction(numbers: List<Int>): Int {
        // Extract numbers from the list

        val numberOne = numbers[0]
        val numberTwo = numbers[1]

        return numberOne - numberTwo
    }

    fun multiply(numbers: List<Int>): Int {
        // Extract numbers from the list

        val numberOne = numbers[0]
        val numberTwo = numbers[1]

        return numberOne * numberTwo
    }

    fun division(numbers: List<Int>): Int {
        // Extract numbers from the list

        val numberOne = numbers[0]
        val numberTwo = numbers[1]

        return numberOne / numberTwo
    }

    fun percent(numbers: List<Int>): Int {
        // Extract numbers from the list

        val numberOne = numbers[0]
        val numberTwo = numbers[1]

        return numberOne * (numberTwo/100)
    }
}