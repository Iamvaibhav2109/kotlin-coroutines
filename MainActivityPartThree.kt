package com.vaibhav.myprepartionapplication.coroutins

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.vaibhav.myprepartionapplication.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import kotlin.system.measureTimeMillis

class MainActivityPartThree : ComponentActivity() {
    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //This is about coroutine async and wait method
        //Whenever we need to make two calls concurrently or you can say call two suspend methods simultaneously
        //than this will work
        //will proof handy.


        GlobalScope.launch(Dispatchers.IO) {
            Log.d(TAG, "Start")
            doNetworkCall1()
            doNetworkCall2()
        }
       //OUTTPUT PRINTS AFTER 6 SECs
       //However there is one method measureInTimes method which measure coroutine execution time
        GlobalScope.launch(Dispatchers.IO) {
            Log.d(TAG, "Start")
            val job= measureTimeMillis {
                doNetworkCall1()
                doNetworkCall2()
            }
          //OUTPUT prints total execution time 6 milisec
        }

        //There is one manual approach by which can we can execute task in 3 sec for both suspend fun
        GlobalScope.launch(Dispatchers.IO) {
            Log.d(TAG, "Start")
            val time = measureTimeMillis {
                val job1 = launch {
                    doNetworkCall1()
                }
                val job2 = launch {
                    doNetworkCall2()
                }
                job1.join()
                job2.join()
                Log.d(TAG, "Answer1 of first network call")
                Log.d(TAG, "Answer2 of second network call")
                //This will execute in 3 milisec as we started different coroutines
            }
        }
            //OUTPUT prints total execution time in 3 milisec

            //Now use async and await
            GlobalScope.launch(Dispatchers.IO) {
                Log.d(TAG, "Start")
                val time= measureTimeMillis {
                    //async is called deferred in here which as deferred<string> returns the result
                    //it starts new coroutine as store the network call result as deferred
                    //here now the this is not a job it is a deferred string so
                    val ans1= async { doNetworkCall1() }
                    val ans2= async { doNetworkCall2() }
                    //here we can not print answer1 as it is deferred so use await()
                    //it will block current coroutine GlobalScope.launch(Dispatchers.IO) this one untill the answer 1 is available
                    //same for answer 2
                    Log.d(TAG, "Answer1 of first network call ${ans1.await()}")
                    Log.d(TAG, "Answer2 of second network call ${ans2.await()}")
                    //This will execute in 3 milisec as we started different coroutines
                }
                // We can also replace GlobalScope.launch with GlobalScope.sync but here coroutine doesn't return anything so not needed

            }
    }
    suspend fun doNetworkCall1() {
        delay(3000L)
        Log.d(TAG, "Answer1 of first network call")
    }

    suspend fun doNetworkCall2() {
        delay(3000L)
        Log.d(TAG, "Answer2 of second network call")
    }
        suspend fun doNetworkCall3():String {
            delay(3000L)
            return "Answer1 of first network call"
        }

        suspend fun doNetworkCall4():String {
            delay(3000L)
            return "Answer2 of second network call"
        }
}