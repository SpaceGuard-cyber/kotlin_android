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

class division{
    fun div(nums: Pair<Pair<Double, Double>, Pair<Double, Double>>):String{
        return ((nums.first.first * nums.second.first + nums.first.second* nums.second.second)
                / (nums.second.second * nums.second.second + nums.second.first *nums.second.first))
            .toString() + (if(((nums.first.second * nums.second.first - nums.first.first * nums.second.second)
                    / (nums.second.second * nums.second.second + nums.second.first * nums.second.first))<0) "" else "+") +
                ((nums.first.second * nums.second.first - nums.first.first * nums.second.second)
                        / (nums.second.second * nums.second.second + nums.second.first * nums.second.first)).toString() + "i"
    }
}

class multiplication {
    fun mul(nums: Pair<Pair<Double, Double>, Pair<Double, Double>>): String {
        return (nums.first.first * nums.second.first - nums.first.second * nums.second.second).toString() +
                (if ((nums.first.second * nums.second.first + nums.first.first * nums.second.second) < 0) "" else "+") +
                (nums.first.second * nums.second.first + nums.first.first * nums.second.second).toString() + "i"
    }
}
// должен знать с какой view работает
class Presenter{

    var mainView: MainView? = null
    val modelMul = multiplication()
    val modelDiv = division()
    fun click(number1: String, number2: String) {
        val number1 = number1.toIntOrNull()
        val number2 = number2.toIntOrNull()
        if (number1==null) modelDiv?.showError("")
    }
}

// Интерфейс View которым будет пользоваться Presenter
interface MainView{
    fun viewResult(result: Double)
    fun showError(error:String)
}

 class MainActivity : AppCompatActivity(), MainView{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val presenter = Presenter()
        presenter.mainView = this

        val editNumber1 = findViewById<EditText>(R.id.number1)
        val editNumber2 = findViewById<EditText>(R.id.number2)

        findViewById<Button>(R.id.div).setOnClickListener{
            presenter.click(editNumber1.text.toString(), editNumber2.text.toString())
        }
    }

     override fun viewResult(result: Double) {
         findViewById<TextView>(R.id.viewResult).text=result.toString()
     }

     override fun showError(error: String) {
        Toast.makeText(this,error,Toast.LENGTH_LONG)
     }
 }