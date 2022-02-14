package com.example.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.`AppCompatButton$InspectionCompanion`
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import org.w3c.dom.Text
import java.math.*
import kotlin.math.abs
import kotlin.math.pow

/*
class calculate {

    private var job:Job?=null

    suspend fun calculate():Double {
        //GlobalScope.launch {
            val number = 2.0
            var n = 1.0
            var res = 0.0
            while (!n.equals(10.0E1)) {
                res += number.pow(n)
                //delay(3000)
                n++
                //launch {
                //    listOf(res).forEach { channel.send(it.toInt()) }
                //}
            //}
        }
        return res
    }


    fun startCalculate(){
        GlobalScope.launch {
            val res = calculate()
            listenerAnswer?.receiveAnswer(res)
        }
    }

    private var listenerAnswer:ListenerAnswer?=null

    fun register(listenerAnswer: ListenerAnswer){
        this.listenerAnswer = listenerAnswer
    }

    companion object{
        private var calculate:calculate? = null
        fun getInstance():calculate{
            if (calculate==null) calculate= calculate()
            return calculate!!
        }
    }
}

interface ListenerAnswer{
    fun receiveAnswer(answer: Double)
}
*/
class MainActivity : AppCompatActivity(){//, ListenerAnswer {

    //lateinit var textView:TextView

    /* flow----------------------------
    fun generate(): Flow<Int> = flow {
        val number = 2.0
        var n = 1.0
        var res = 0.0
        while (!n.equals(10.0)) {
            res += number.pow(n)
            n++
            listOf(res).forEach {
                delay(3000)
                emit(it.toInt()) }
        }
    } ----------------------------------*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //calculate.getInstance().register(this)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.buttonCoroutine)
        val textView = findViewById<TextView>(R.id.textView)
        val cancelButton = findViewById<Button>(R.id.cancel_button)
        var job: Job?=null
        button.setOnClickListener {
            //calculate.getInstance().startCalculate()
            MyIntentService.startActionNUMBER(this)
            job = GlobalScope.launch {
             val channel = Channel<Int>(10) // отсюда можно читать и сюда можно передавать
                val number = BigInt(1)
                var n = 1.0
                var res = 0.0
                while (!n.equals(10.0E9)) {
                    res += number.pow(n)
                    delay(300)
                    n++
                    launch {
                        listOf(res).forEach { channel.send(it.toInt()) }
                    }
                    launch(Dispatchers.Main) {
                        textView.text = res.toString()
                    }
                }
            }
        /* flow ---------------------------------------------------------------------------------------
        GlobalScope.launch(Dispatchers.Main) {
            generate().filter { it%2==0 }.collectLatest{
                value -> Toast.makeText(this@MainActivity, value.toString(), Toast.LENGTH_LONG).show()
            }
        }----------------------------------------------------------------------------------------------*/

            /* Coroutine------------------------------------------------------------------------------------
            GlobalScope.launch {
                val channel = Channel<Int>(10) // отсюда можно читать и сюда можно передавать
                val number = 2.0
                var n = 1.0
                var res = 0.0
                while (!n.equals(10.0)) {
                    res += number.pow(n)
                    n++
                    launch {
                        listOf(res).forEach { channel.send(it.toInt()) }
                        //channel.close()
                    }
                    launch(Dispatchers.Main) {
                        repeat(10) {
                            val n = channel.receive()
                        //for (n in channel){
                            Toast.makeText(this@MainActivity, n.toString(), Toast.LENGTH_LONG).show()
                            delay(3000)
                        }
                    }
                }
            } -------------------------------------------------------------------------------------------------*/
        }
        cancelButton.setOnClickListener {
            job?.cancel()
        }
    }

    /*override fun receiveAnswer(answer: Double) {
        GlobalScope.launch(Dispatchers.Main){
        textView.text = answer.toString()
        }
    }*/
}

