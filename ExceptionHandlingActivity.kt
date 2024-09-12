package com.vaibhav.myprepartionapplication.coroutins

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.vaibhav.myprepartionapplication.R
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class ExceptionHandlingActivity : ComponentActivity() {
    val TAG="MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //This is a example how we can use exception handling in coroutines
        //in coroutine if weh have coroutine and its child coroutines and one of child coroutines
        //throws exeception than it will propogate to outer coroutine and if doesn't handled than
        //it will crashed/.

        //How ever cancel() method also occur    excption but it is handled by pre defined coroutines
        // so our program will not crashed

        lifecycle.launch {
            try{
                println("Exception handling")
                throw Exception("Error")
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
        //this will not work in coroutines as this is not a right wark

        lifecycle.launch {
           val string=async{
                delay(500L)
                println("Exception handling")
                throw Exception("Error")
            }
        }
        //The above method uses async within a launch so it will also give error as soons as we use await()
        //as it will propogate to launch which directly give the error but if we make it async it will not give error
        //lifecycle.async

       val def= lifecycle.async {
            val string=async{
                delay(500L)
                println("Exception handling")
                throw Exception("Error")
            }
        }
        ///this will not give crash the program

        lifecycle.launch {
            def.await()//now we get application crashed as we uses launch and await().
            //async is also give error but hold untill we call await() but on other hand launch gets error instantly
        }


        //Although we can use try and catch in above coroutine to handle the scenario but that is not good practice

        //
        val handler= CoroutineExceptionHandler{_,throwable->
            println("Error caught")
        }
        //we will install this handler in our root coroutine always

        lifecycle.launch(handler) {
                println("Exception handling")
                throw Exception("Error")
            }
        //this will work
        //Also the handler only works for uncaught exceptions not for only handled exceptions

        //If we have multiple child coroutines and one of the coroutine throws exception that
        //other child ones don't execute if we use coroutinescope. to not obstract other child we can use
        //supervisorScope which will not afect other ones due to exceptions.

        //one way to use custom coroutine scope is
        //CoroutineScope(Dispatchers.IO+hanler).launhc{}


        //we can somehow use try and catch block in launch but not useful in child coroutines
        //as wit will not propgate to root coroutine due to eaten up in try and catch block.


    }
}