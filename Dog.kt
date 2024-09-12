package com.vaibhav.myprepartionapplication.OOPS

open class Dog(private val name: String, private val breed: String) : Animal() {

    init {
        //Call as soon as object created
        println("Dog class init block: Name is $name, Breed is $breed")
    }

    //Secondary constructor which doesn't need val or var in params and needs to identify which typw so used
    //this() which is primary constructor
    constructor(name: String) : this(name, "Unknown Breed") {
        println("Secondary constructor: Name is $name, Breed is unknown")
    }

    override fun sound(): String {
        return "Bark"
    }

    override fun move(): String {
        return "Run"
    }

    fun fetch() {
        println("$name is fetching!")
    }

    fun fetch(times: Int) {
        println("$name is fetching $times times!")
    }

    //A companion object in Kotlin is an object that is tied to a specific class and
    // allows for defining static-like methods and properties.
    // Companion objects are declared inside a class using the companion object keyword.
    // They provide a way to group related functionality, such as factory methods, within the class
    // they are associated with.
    companion object {
        fun createUnknownBreedDog(name: String): Dog {
            return Dog(name, "Unknown Breed")
        }
    }
}
