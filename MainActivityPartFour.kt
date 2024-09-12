package com.vaibhav.myprepartionapplication.coroutins

import android.content.Intent
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

class MainActivityPartFour : ComponentActivity() {
    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //This is about coroutine lifecycleScope and viewModelScope
        // Why? The proble with GlobalScope is that it runs untill the application runs so there might be
        //memory leak we don't need it lets close it.
        //for an example I've made two coroutines. One coroutine starts on button click and run in
        //infinitely loop and than we start another coroutine and start a new activity so Once new activity launch
        //The first coroutine is still going as we used globalscope and not dismiss the app

        //btn.setOnClickListener{
        GlobalScope.launch {
            while(true){
                delay(1000L)
                Log.d(TAG,"Still running")
            }
        }
        GlobalScope.launch {
                delay(5000L)
                Intent(this@MainActivityPartFour,MainActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
        }
        //Here after 5 sec the Main Activity will open but our first coroutine is still going on
        //To avoid this we can use lifecyclescope
       /* //lifecycle.launch{
            while(true){
                delay(1000L)
                Log.d(TAG,"Still running")
            }
        }*/
        //lifecycle scope is bound with activity. viewModelScope is similar it is just bound till viewmODEL
        //exist if it cancel it stops.
    }
}