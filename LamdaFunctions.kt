package com.vaibhav.myprepartionapplication.BasicKotlinFeatures

class LamdaFunctions {
        fun main(args: Array<String>) {
            val person1 = Person("Alice", 30)
            val person2 = Person("Bob", 25)
            val person3 = Person("Charlie", 35)
            val person4 = Person("Diana", 28)

            val people = listOf(person1, person2, person3, person4)
            val names = listOf("Charlie", "Alice", "Bob", "Diana")

            // Using a standard library function (filter) with a lambda
            val adults = people.filter { it.age >= 30 }
            println("Adults: ${adults.map { it.name }}")

            // Using a custom function with a lambda
            //Function Call Parameters (using ())
            //The () is used to enclose the arguments that are passed to the function.
            // This includes both regular arguments and lambda expressions.
            //Lambda Expression (using {}):
            //The {} is used to define the lambda expression itself. This is essentially an anonymous function you pass to the function being called.
            val sortedNames= sortListAlphabetically(names) { s1, s2 -> s1.compareTo(s2)
            }
            println("Sorted Names: $sortedNames")
        }
    // Custom function that sorts a list of strings alphabetically using a lambda
    //: List<String>  is return type function
    //comparator: (String, String) -> Int this is lamda part where comparator is function name strings are params
    //and -> indicates return type
    private fun sortListAlphabetically(list: List<String>, comparator: (String, String) -> Int): List<String> {
        return list.sortedWith(Comparator(comparator))
    }
}
