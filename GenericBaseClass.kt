package com.vaibhav.myprepartionapplication.BasicKotlinFeatures

class GenericBaseClass {
    fun main() {
        val stringBox = Generic("Hello")
        val intBox = Generic(123)

        println(stringBox.content) // Output: Hello
        println(intBox.content) // Output: 123

        stringBox.content = "World"
        println(stringBox) // Output: Box(content=World)

        val intArray = arrayOf(1, 2, 3, 4, 5)
        val stringArray = arrayOf("one", "two", "three")

        printArray(intArray) // Output: 1 2 3 4 5
        printArray(stringArray) // Output: one two three
    }
    //Example for Gneric function
    fun <T> printArray(array: Array<T>) {
        for (element in array) {
            print("$element ")
        }
        println()
    }
}