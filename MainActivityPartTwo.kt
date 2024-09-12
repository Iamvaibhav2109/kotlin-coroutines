package com.vaibhav.myprepartionapplication.coroutins

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.vaibhav.myprepartionapplication.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout

class MainActivityPartTwo : ComponentActivity() {
    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //This is about coroutine jobs , join waiting and other functions.
        //Whenever we make any coroutines it will return a job the launch() retun the job
        //if we want execute some other  coroutines after execution of any other coroutines this
        //will proof handy.


        val job=GlobalScope.launch {
            repeat(5){
                Log.d(TAG,"This will execute 5 times")
                delay(3000L)
            }
        }
        //we can not call job.join outside coroutines as it is suspend function
        //We can use runblocking
        //Join method waits for execution of one coroutine to another
        //OUTPUT 1:  This will execute 5 times, This will execute 5 times,This will execute 5 times
        //This will execute 5 times,This will execute 5 times,This will execute after first coroutine executed 5 times
        runBlocking {
            job.join()
            Log.d(TAG,"This will execute after first coroutine executed 5 times")
        }


        //USE OF CANCELLATION
        //We can cancel the job if it is not required for that we can use cancel()
        val job2=GlobalScope.launch {
          repeat(5){
              Log.d(TAG,"CANCEL")
              delay(1000L)
          }
        }
        runBlocking {
            delay(2000L)
            job2.cancel()
            Log.d(TAG,"Main thread execution")
        }
        //Here the OUTPUT stops after two iteration as we cancelled the job after sec so
        //it will print CANCEL, CANCEL, Main thread execution

        //Drawback ---This is a manual way of cancelling the job however it is quite risky as here
        //we're using suspend delay method so we have tracking at which time it will cancelled
        //But there might be scenarios where there is no suspend and delay methods
        //in that case the coroutins is busy in executtig  task as it doesn't cancel coroutine
        //Example
        val job3=GlobalScope.launch{
            Log.d(TAG,"Starting long caluclations=====")
            for(i in 30..40){
                Log.d(TAG,"Result for i= $i ${fib(i)}")
            }
            Log.d(TAG,"Ending long caluclations=====")
        }
        runBlocking {
            delay(2000L)
            job3.cancel()
            Log.d(TAG,"Main thread running")
        }
        //OUTPUT : it will execute all 31 to 40 result although we reached the state after 2 sec
        //to cancel the job as coroutine is busy in recursive execution
        // to improve this we can use isActive to notify that it is cancel manually
        val job4=GlobalScope.launch{
            Log.d(TAG,"Starting long caluclations=====")
            if(isActive) {
                for (i in 30..40) {
                    Log.d(TAG, "Result for i= $i ${fib(i)}")
                }
            }
            Log.d(TAG,"Ending long caluclations=====")
        }
        // this check will check if coroutine is cancelled or not
        // this a manual approach and can lead to error
        // the good way is to use withTimeout method in above code

        val job5=GlobalScope.launch{
            Log.d(TAG,"Starting long caluclations=====")
            withTimeout(3000L) {
                if (isActive) {
                    for (i in 30..40) {
                        Log.d(TAG, "Result for i= $i ${fib(i)}")
                    }
                }
            }
            Log.d(TAG,"Ending long caluclations=====")
        }
        //With this we also don't need runblocking as itselef cancel the coroutine after 3 sec
        //so we don't need to call coroutine manually
    }
    fun fib(n:Int):Long {
        return if(n==0) 0
        else if(n==1) 1
        else fib(n-1) + fib(n-2)
    }
}