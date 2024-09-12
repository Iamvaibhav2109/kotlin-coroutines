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

class RetrofitUseActivity : ComponentActivity() {
    val TAG="MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //This is a example how we can use retrofit with coroutines
       /* val api=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConerterFactory(GsonConerterFactory.create())
            .build()
            .create(MyAPI::class.java)
       */
        GlobalScope.launch {
            //This is the way we can directly get the body from response
            /*val responseBody=api.getComments().await()
            for(comment in responseBody){
                Log.d(TAG,comment.toString)()
            }*/
            //but if we need the whole response

            /*val response=api.getComments().awaitResponse()
            for(comment in response.body()!!{
                Log.d(TAG,comment.toString)()
            }*/

        }
           Log.d(TAG,"This is coroutine and its name is ${Thread.currentThread().name}")
         }

        //this is the one approach we can do but there is one other way to do the same
    //here getComments is a API call method in interface so we can make that method suspend and return response d
    //directly from there. by this we don't need await() or awaitResponse() methods as getComments is
    //already a suspend method.
    /*val responseBody=api.getComments()
            for(comment in responseBody){
                Log.d(TAG,comment.toString)()
            }*/
    //This will work the same.
}