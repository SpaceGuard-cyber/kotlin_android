package com.example.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


// MVP - model view presenter
// model - логика+хранение+получение
// view - объекты взаимодействия с пользователем + view
// presenter - он получает данные view, отправляет в model, получает данные из model и сообщает view что надо делать

class CalculateTwoComplexNumbersModel{
    fun mulForRe (a: Double, b: Double, c: Double, d: Double) = a * c - b * d
    fun mulForIm (a: Double, b: Double, c: Double, d: Double) = a * d + b * c
    fun divForRe (a: Double, b: Double, c: Double, d: Double) = (((a * c) + (b * d)) / ((c * c) + (d * d)))
    fun divForIm (a: Double, b: Double, c: Double, d: Double) = (((b * c) - (a * d)) / ((c * c) + (d * d)))
}

// Интерфейс View которым будет пользоваться Presenter
interface CalculateTwoComplexNumberView{
    fun viewResultRe(result: Double)
    fun viewResultIm(result: Double)
    fun viewError(error: String)
}

// должен знать с какой view работает
class Presenter{
    private val model = CalculateTwoComplexNumbersModel()

    var calculateTwoComplexNumberView: CalculateTwoComplexNumberView? = null

    var lastResultRe: Double? = null
    var lastResultIm: Double? = null

    fun clickMul(a:String, b:String, c:String, d:String){
        if(a.toDoubleOrNull() != null){
            if(b.toDoubleOrNull() != null){
                if (c.toDoubleOrNull() != null){
                    if(d.toDoubleOrNull() != null){
                        lastResultRe = model.mulForRe(a.toDouble(),b.toDouble(),c.toDouble(),d.toDouble())
                        lastResultIm = model.mulForIm(a.toDouble(),b.toDouble(),c.toDouble(),d.toDouble())
                        calculateTwoComplexNumberView?.viewResultRe(lastResultRe!!)
                        calculateTwoComplexNumberView?.viewResultIm(lastResultIm!!)
                    }
                    else calculateTwoComplexNumberView?.viewError("Haven't d")
                }
                else calculateTwoComplexNumberView?.viewError("Haven't c")
            }
            else calculateTwoComplexNumberView?.viewError("Haven't b")
        }
        else calculateTwoComplexNumberView?.viewError("Haven't a")
    }

    fun clickDiv(a:String, b:String, c:String, d:String){
        if(a.toDoubleOrNull() != null){
            if(b.toDoubleOrNull() != null){
                if (c.toDoubleOrNull() != null){
                    if(d.toDoubleOrNull() != null){
                        lastResultRe = model.divForRe(a.toDouble(),b.toDouble(),c.toDouble(),d.toDouble())
                        lastResultIm = model.divForIm(a.toDouble(),b.toDouble(),c.toDouble(),d.toDouble())
                        calculateTwoComplexNumberView?.viewResultRe(lastResultRe!!)
                        calculateTwoComplexNumberView?.viewResultIm(lastResultIm!!)
                    }
                    else calculateTwoComplexNumberView?.viewError("Haven't d")
                }
                else calculateTwoComplexNumberView?.viewError("Haven't c")
            }
            else calculateTwoComplexNumberView?.viewError("Haven't b")
        }
        else calculateTwoComplexNumberView?.viewError("Haven't a")
    }
    fun afterAttach(){
        if(lastResultRe!=null){
            if (lastResultIm!=null){
                calculateTwoComplexNumberView?.viewResultIm(lastResultIm!!)
                calculateTwoComplexNumberView?.viewResultRe(lastResultRe!!)
            }
        }
    }
}

class Context private constructor(){
    private val presenter = Presenter()
    fun getPresenter(): Presenter{
        return presenter
    }
    companion object{
        private var context: Context? = null
        fun get(): Context{
            if(context==null){
                context = Context()
            }
            return context!!
        }
    }
}


 class MainActivity : AppCompatActivity(), CalculateTwoComplexNumberView{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val presenter = Context.get().getPresenter()
        presenter.calculateTwoComplexNumberView = this
        presenter.afterAttach()

        val editNumber1 = findViewById<EditText>(R.id.number1)
        val editNumber2 = findViewById<EditText>(R.id.number2)
        val editNumber3 = findViewById<EditText>(R.id.number3)
        val editNumber4 = findViewById<EditText>(R.id.number4)

        findViewById<Button>(R.id.multiply).setOnClickListener {
            presenter.clickMul(editNumber1.text.toString(), editNumber2.text.toString(), editNumber3.text.toString(), editNumber4.text.toString())
        }

        findViewById<Button>(R.id.div).setOnClickListener{
            presenter.clickDiv(editNumber1.text.toString(), editNumber2.text.toString(), editNumber3.text.toString(), editNumber4.text.toString())
        }
    }

     override fun viewResultRe(result: Double) {
         findViewById<TextView>(R.id.viewResultRe).text = result.toString()
     }

     override fun viewResultIm(result: Double) {
         findViewById<TextView>(R.id.viewResultIm).text = result.toString()
     }

     override fun viewError(error: String) {
         Toast.makeText(this, error, Toast.LENGTH_LONG).show()
     }
 }