package com.example.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.EditText
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

class FactCalculations {

    private var job: Job? = null

    private var listenerResult: ListenerResult? = null

    private fun calculateFactorial(n: Int): BigInteger {
        var fact = BigInteger.ONE
        for (i in 1..n) {
            fact = fact.multiply(BigInteger.valueOf(i.toLong()))
        }
        return generateSequence(fact) { it - BigInteger.valueOf(2) }.takeWhile { (it > BigInteger.ZERO) && !(job?.isCancelled!!) }.reduce { acc, c -> acc*c }
    }
    @DelicateCoroutinesApi
    fun startCalculations(n: Int) {
        job = GlobalScope.launch {
            val fact = calculateFactorial(n)
            listenerResult?.receiverResult(fact)
        }
    }
    fun stopCalculations() {
        job?.cancel()
    }
    fun register(listenerResult: ListenerResult) {
        this.listenerResult = listenerResult
    }
    companion object {
        private var calculationsResult: FactCalculations? = null
        fun getInstance(): FactCalculations {
            if (calculationsResult == null) calculationsResult = FactCalculations()
            return calculationsResult!!
        }
    }
}

interface ListenerResult {
    fun receiverResult(result: BigInteger)
}
class MainActivity : AppCompatActivity(),ListenerResult {

    private lateinit var viewResult: TextView

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(RESULT, viewResult.text.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FactCalculations.getInstance().register(this)

        val enterNumber = findViewById<EditText>(R.id.enterNumber)
        val calcFact = findViewById<Button>(R.id.calculateFactorialButton)
        val stopCalc = findViewById<Button>(R.id.stopCalculationsButton)
        viewResult = findViewById(R.id.viewAnswer)

        var job: Job? = null

        if (savedInstanceState != null) {
            viewResult.text = savedInstanceState.getString(RESULT)
        }

        calcFact.setOnClickListener {
            val num = enterNumber.text.toString().toIntOrNull()

            if (num != null) {
                if (num in 1..9) {
                    job = GlobalScope.launch {
                        FactCalculations.getInstance().startCalculations(num)
                    }
                } else Toast.makeText(this, getString(R.string.NumberRange), Toast.LENGTH_SHORT).show()
            } else Toast.makeText(this, getString(R.string.EmptyString), Toast.LENGTH_SHORT).show()
        }

        stopCalc.setOnClickListener {
            FactCalculations.getInstance().stopCalculations()
        }
    }

    override fun receiverResult(result: BigInteger) {
        GlobalScope.launch(Dispatchers.Main) {
            viewResult.text = String.format("%d", result)
        }
    }

    companion object {
        const val RESULT = "Result"
    }
}
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
    /*override fun receiveAnswer(answer: Double) {
        GlobalScope.launch(Dispatchers.Main){
        textView.text = answer.toString()
        }
    }*/
} -------------------------------------------------------------------------------------------------*/

