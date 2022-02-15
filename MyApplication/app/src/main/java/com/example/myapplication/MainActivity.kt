package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Entity




class CalculateTwoComplexNumbers{
    fun mulforre (a: Double, b: Double, c: Double, d: Double) = a * c - b * d
    fun mulforim (a: Double, b: Double, c: Double, d: Double) = a * d + b * c
    fun divforre (a: Double, b: Double, c: Double, d: Double) = (((a * c) + (b * d)) / ((c * c) + (d * d)))
    fun divforim (a: Double, b: Double, c: Double, d: Double) = (((b * c) - (a * d)) / ((c * c) + (d * d)))
}



class MainActivity : AppCompatActivity() {
    lateinit var TextViewResultre: TextView
    lateinit var TextViewResultim: TextView

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putString(RESULTRE, TextViewResultre.text.toString())
        outState.putString(RESULTIM, TextViewResultim.text.toString())
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val textViewre = findViewById<TextView>(R.id.textViewre)
        outState.putString(RESULTRE, textViewre.text.toString())

        val textViewim = findViewById<TextView>(R.id.textViewim)
        outState.putString(RESULTIM, textViewim.text.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val buttonSend = findViewById<Button>(R.id.buttonsend)
        val editTexta = findViewById<EditText>(R.id.editTextTexta)
        val editTextb = findViewById<EditText>(R.id.editTextTextb)
        //val editTexti1 = findViewById<EditText>(R.id.editTextTexti1)
        val editTextc = findViewById<EditText>(R.id.editTextTextc)
        val editTextd = findViewById<EditText>(R.id.editTextTextd)
        //val editTexti2 = findViewById<EditText>(R.id.editTextTexti2)
        TextViewResultre = findViewById<TextView>(R.id.textViewre)
        if (savedInstanceState != null)
            TextViewResultre.text = savedInstanceState.getString(RESULTRE)
        TextViewResultim = findViewById<TextView>(R.id.textViewim)
        if (savedInstanceState != null)
            TextViewResultim.text = savedInstanceState.getString(RESULTIM)
        val buttonDiv = findViewById<Button>(R.id.buttondiv)
        val buttonMul = findViewById<Button>(R.id.buttonmul)
        //val editTextForSend = findViewById<EditText>(R.id.editTextForSend)
       /* buttonSend.setOnClickListener{
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,editTextForSend.text.toString())
            intent.type = "text/plain"
            val intentCreateСhooser = Intent.createChooser(intent,null)
            startActivity(intentCreateСhooser)
        }*/

        buttonMul.setOnClickListener {
            val re: Double
            val im: Double
            val numberaText = editTexta.text
            val numberbText = editTextb.text
            val numbercText = editTextc.text
            val numberdText = editTextd.text

            val a = numberaText.toString().toDoubleOrNull()
            val b = numberbText.toString().toDoubleOrNull()
            val c = numbercText.toString().toDoubleOrNull()
            val d = numberdText.toString().toDoubleOrNull()

            // a + bi - первое число
            // c + di - второе число
            // number1.i1 * number2.i1 - number1.i2 * number2.i2
            if (a != null)
                if (b != null)
                    if (c != null)
                        if (d != null) {
                            val CalculateTwoComplexNumbers = CalculateTwoComplexNumbers()
                            re = CalculateTwoComplexNumbers.mulforre(a,b,c,d)
                            im = CalculateTwoComplexNumbers.mulforim(a,b,c,d)
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(RESULTRE, re)
                            intent.putExtra(RESULTIM, im)
                            if (re.toString() != "NaN")
                                if (re.toString() != "Infinity")
                                    if (re.toString() != "-Infinity")
                                        //TextViewResultre.text = re.toString()
                            if (im.toString() != "NaN")
                                if (im.toString() != "Infinity")
                                    if (im.toString() != "-Infinity")
                                        startActivity(intent)
                                    //TextViewResultim.text = im.toString()
                        } else {
                            Toast.makeText(
                                this,
                                getString(R.string.second_number_error_d),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    else {
                        Toast.makeText(
                            this,
                            getString(R.string.second_number_error_c),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                    Toast.makeText(
                        this,
                        getString(R.string.first_number_error_b),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                Toast.makeText(
                    this,
                    getString(R.string.first_number_error_a),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        buttonDiv.setOnClickListener {
            val re: Double
            val im: Double
            val numberaText = editTexta.text
            val numberbText = editTextb.text
            val numbercText = editTextc.text
            val numberdText = editTextd.text

            val a = numberaText.toString().toDoubleOrNull()
            val b = numberbText.toString().toDoubleOrNull()
            val c = numbercText.toString().toDoubleOrNull()
            val d = numberdText.toString().toDoubleOrNull()

            // a + bi - первое число
            // c + di - второе число
            if (a != null)
                if (b != null)
                    if (c != null)
                        if (d != null)
                            if (c * c + d * d != 0.0) {
                                val CalculateTwoComplexNumbers = CalculateTwoComplexNumbers()
                                re = CalculateTwoComplexNumbers.divforre(a,b,c,d)
                                im = CalculateTwoComplexNumbers.divforim(a,b,c,d)
                                val intent = Intent(this, ResultActivity::class.java)
                                intent.putExtra(RESULTRE, re)
                                intent.putExtra(RESULTIM, im)
                                if (re.toString() != "NaN")
                                    if (re.toString() != "Infinity")
                                        if (re.toString() != "-Infinity")
                                            //TextViewResultre.text = re.toString()
                                if (im.toString() != "NaN")
                                    if (im.toString() != "Infinity")
                                        if (im.toString() != "-Infinity")
                                            startActivity(intent)
                                            //TextViewResultim.text = im.toString()
                            } else {
                                Toast.makeText(
                                    this,
                                    getString(R.string.div_error),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        else {
                            Toast.makeText(
                                this,
                                getString(R.string.second_number_error_d),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    else {
                        Toast.makeText(
                            this,
                            getString(R.string.second_number_error_c),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                    Toast.makeText(
                        this,
                        getString(R.string.first_number_error_b),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                Toast.makeText(
                    this,
                    getString(R.string.first_number_error_a),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    companion object {
        const val RESULTRE = "RESULTRE"
        const val RESULTIM = "RESULTIM"
    }
}