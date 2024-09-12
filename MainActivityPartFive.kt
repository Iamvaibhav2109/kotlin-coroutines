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

data class Person(
    val personName:String="",
    val age:Int=0
)
class MainActivityPartFive : ComponentActivity() {
    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //This is about coroutine with firestore
        //Firestore is open source firebase database
        //Scenario we need to make chat object. on that there might ne multiple network calls that depends on
        // another network call so for that we need to make user1 call back first than user 2 and than get message task
        //afterwards amke chatobject that can be very bad approach but this we can achieve with coroutines
        //As they are esy to pause and play without blockin the thread.

        //val tutorial = Firebase.firestore.collection("coroutines").document("tutorial")
        //read project and document from firestore
        //val peter=Person("Peter","25")
        //GlobalScope.launch(Dispatchers.IO){
        // delay(3000L)
        // tutorial.set(peter).await()
        // val person=tutorial.get().await().toObject(Person::class.java)
        // withContext(Dispatchers.Main){tvdata.text=person.toString()}
        // }

        //In above code we're setting data on tutorial firestore with the use of await() method
        //without await if we set the data it won' tus our coroutine logic for that we need to use
        // suspend function await() which doesn't block the thread and execute the data to set on firestore
        //document

        //set method from firestore returns a task and await execute this task()
        //get is also a task
        //We need convert the data into Kotlin object so we need to call toObject and provide a class to know
        //kotlin to interpet the data

        //Now we need to upodat the data in UI so used withContext METHOD

        //we can also use onSuccessTask{} block in place of await as well but await works well
    }
}