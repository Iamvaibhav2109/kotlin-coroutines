package com.vaibhav.myprepartionapplication.BasicKotlinFeatures

class VarargDefaultNamedparameters {
    fun main(){
        //For VarArg variant 1
        val max=getMax(1,2,7,8,9,3,4,6)
        //For VarArg variant 2
        val array= intArrayOf(10,20,25)
        val max2=getMax(1,2,5,5,15,12,13,*array,19)

        //Default params variant 1
        searchFor("I'm practicing kotlin")
        searchFor("I'm changing","TO java")

        //Named Parameters if lots of arguments and don't knwo the order
        searchFor(search="xyz", searchString = "ABC")
    }
    fun getMax(vararg numbers:Int):Int{
        var max=numbers[0];
        for(number in numbers){
            if(number>max)
                max=number;
        }
        return max;
    }

    //Default PARAMS
    fun searchFor(search:String, searchString:String="Google"){
        println("My searched string is $searchString")
    }
}