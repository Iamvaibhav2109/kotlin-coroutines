package com.vaibhav.myprepartionapplication.OOPS

object MainClass {
    @JvmStatic
    fun main(args: Array<String>) {
        val dog1 = Dog("Buddy", "Golden Retriever")
        println("${dog1.sound()} and ${dog1.move()}")
        dog1.fetch()
        dog1.fetch(3)

        val dog2 = Dog("Max")
        println("${dog2.sound()} and ${dog2.move()}")

        //Used factory method as createUnknownBreedDog returns Dog instance so we can call Dog methods.
        val unknownBreedDog = Dog.createUnknownBreedDog("Shadow")
        println("${unknownBreedDog.sound()} and ${unknownBreedDog.move()}")
        unknownBreedDog.fetch()


        // Example of using anonymous class
        //An anonymous class in Kotlin is a way to create an instance of an object with
        // certain modifications without declaring a new subclass for it
        val specialDog = object : Dog("Rex", "Labrador") {
            override fun sound(): String {
                return "Howl"
            }

            override fun move(): String {
                return "Gallop"
            }
        }

        println("${specialDog.sound()} and ${specialDog.move()}")
        specialDog.fetch()
        specialDog.fetch(2)

        println(CatHelper.meow())
        println(CatHelper.nap())
        println(CatHelper.className)
    }
}