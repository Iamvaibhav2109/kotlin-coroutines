package com.vaibhav.myprepartionapplication.BasicKotlinFeatures

//This class can hold an object of any type, specified when an instance of the class is created.
//<T> means it can take any type of value that tha logic for generic, it also take any type of content
class Generic<T>(var content: T) {
        override fun toString(): String {
            return "Box(content=$content)"
        }
    }