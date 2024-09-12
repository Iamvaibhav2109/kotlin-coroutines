package com.vaibhav.myprepartionapplication.BasicKotlinFeatures

class ExtensionFunctions {
    fun main() {
        println("Please enter a name")
        val input= readLine()?.toInt();
        if(input!=null){
            if(input.isPrime()){
                println("$input is a prime number")
            }
            else{
                println("$input is not a prime number")
            }
        }

    }
    private fun Int.isPrime():Boolean{
        for(i in 2 until this-1){
          if(this%i==0)
            return false;
        }
        return true;
    }

    //Normal function if only one line is present this is not related extension
    fun pow(base:Int,extension:Int)=base*extension
    //Variants of for loop
    fun forLoop(){
        for(i in 0..10){
            //prints 0 to 10
        }
        for(i in 10 downTo 5){

        }
        for(i in 10 downTo 5 step 2){

        }
        for(i in 'a'..'z'){

        }
    }

    fun listExample(){
        val list= listOf(1,2,3,4,5) //list with values
        val list2=listOf<Int>() //Empty list
        // Both are immutable in kotlin
        // for Mutable list
        val list3 = mutableListOf<Int>()
    }
}