package com.vaibhav.myprepartionapplication.coroutins

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.vaibhav.myprepartionapplication.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    val TAG="MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //This is our coroutine and inside it we write instruction to exceute.
        //GlobalScope:: Means it is a coroutine scope and live as long as application live but if it finishes
        //its task and no longer needed than removed
        //This will create seperate thread means not made in main thread. it work as asynchronously.
        GlobalScope.launch {
            delay(3000L)
           Log.d(TAG,"This is coroutine and its name is ${Thread.currentThread().name}")
         }

        //Main Thread
        Log.d(TAG,"This is coroutine and its name is ${Thread.currentThread().name}")


        //As we talk about thread suspend or block we are using sleep in thread but in coroutine we use delay for
        //the same
        // it works asynchronisly as if we close mainThread before exceution of coroutines's thread than c=thread will not execute
        //IMP: Delay works differently as it pause current coroutine only not a thread. but sleep blocks whole thread.

        //SUSPEND FUNCTION
        //Delay is a suspend function. A suspend function can only call inside coroutiune and another suspend function
        //If we can call two suspend function withing a single coroutine and call delay on both functions the delay would be added as
        // it suspend whole coroutine

        GlobalScope.launch {
            //This will call after 6 sec as written in same coroutine
            val ans1=doNetworkCall()
            val ans2=doNetworkCall2()
            Log.d(TAG,ans1)
            Log.d(TAG,ans2)
        }

        //ForContextSwtiching
        GlobalScope.launch(Dispatchers.IO) {
            //Dispatchers is way to define context of the coroutine it has 4 types
            //Dispatchers.IO -> For any input/output needed from any other thread (writing on DB, Network calls)
            //Dispatchers.Main -> runs in main Thread
            //Dispathcher.Default -> for large data and complex data which doesn't block main Thread
            //Dispatcher.Unconfines -> it will stay with suspend function when it pause and resume
            //Also we can make our own context with newSingleThreadContext

            //Now lets start a scenario where we are waiting for some network call and
            //after that we need to update the UI which is on MainThread. As we avoid network calls on we need to
            // made differnret thread and than conetxt switch to main for UI.
            //Here we used Dispatchers.IO as we waiting for network call.
            val ans1=doNetworkCall()
            //Completes network call now switching context in main
            Log.d(TAG,"Starting different thread ${Thread.currentThread().name}")
            withContext(Dispatchers.Main){
                Log.d(TAG,"Starting Main thread ${Thread.currentThread().name}")
                //Perform UI operation
            }

            Log.d(TAG, "Before of runBlocking")
            //runBlocking at its use
            //Sometimes we need to block the thread from coroutines as there might be some need we need to
            // block whole thread than we use coroutine.
            runBlocking {
                //If we want to call suspend method from main thread than will use runBlocking
                //it always affect mainThread
                //If we call any suspend function it will block the main thread.
                //we can start the new coroutine inside it which is not blocked if we call any
                //suspend inside runBlocking also we didn't need GlobalScope as runBlocking is also a CoroutineScope
                //Also we start two coroutines it will not add up the delay they work synchronisly
                launch (Dispatchers.IO){
                  delay(3000L)
                  Log.d(TAG, "Coroutine1")
                }
                launch (Dispatchers.IO){
                    delay(3000L)
                    Log.d(TAG, "Coroutine1")
                }
                Log.d(TAG, "Start of runBlocking")
                delay(3000L)
                Log.d(TAG, "End of runBlocking")

            }
            Log.d(TAG, "After of runBlocking")
            //OUTPUT:Before of runBlocking,
            //Start of runBlocking
            //than after 3 milisec Coroutine1,Coroutine2
            //and after 3 sec prints End of runBlocking,After of runBlocking

        }
    }
        suspend fun doNetworkCall():String{
        delay(3000L)
           return "FirstNetwork call completed!!"
    }

    suspend fun doNetworkCall2():String{
        delay(3000L)
        return "SecondNetwork call completed!!"
    }



}