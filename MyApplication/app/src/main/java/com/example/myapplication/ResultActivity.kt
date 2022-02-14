package com.example.myapplication

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Entity
data class ResultComplexNumber(
    @PrimaryKey(autoGenerate = true ) val uid: Int = 0,
    @ColumnInfo(name="number") val complexNumber: String
)

@Dao
interface ResultComplexNumberDao{
    @Query("SELECT * FROM ResultComplexNumber")
    fun getAll(): LiveData<List<ResultComplexNumber>>

    @Insert
    suspend fun insert(resultComplexNumberDataBase: ResultComplexNumber)

    @Delete
    suspend fun delete(resultComplexNumberDataBase: ResultComplexNumber)

    //@Query("SELECT MAX(uid) FROM ResultComplexNumber")
    //fun getLastResult(): List<ResultComplexNumber>
}

@Database(entities = [ResultComplexNumber::class], version = 1, exportSchema = false)
abstract class ResultComplexNumberDataBase:RoomDatabase(){
    abstract fun resultComplexNumberDao():ResultComplexNumberDao
}


class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val resultTextViewre = findViewById<TextView>(R.id.resultre)
        val resultTextViewim = findViewById<TextView>(R.id.resultim)
        val checkLastResult = findViewById<Button>(R.id.buttonCheckLastResult)
        val lastresult = findViewById<TextView>(R.id.textViewLastResult)


        val db = Room.databaseBuilder(applicationContext,ResultComplexNumberDataBase::class.java, "ResultCalcComplexNumber").build()

        val resultre = intent.getDoubleExtra(MainActivity.RESULTRE, 0.0)
        val resultim = intent.getDoubleExtra(MainActivity.RESULTIM, 0.0)

        resultTextViewre.text = resultre.toString()
        resultTextViewim.text = resultim.toString()

        val resultComplexNumber = ResultComplexNumber(0,"$resultre $resultim")

        GlobalScope.launch(Dispatchers.IO){
            //val resultComplexNumber = ResultComplexNumber(0,"$resultre $resultim")
            db.resultComplexNumberDao().insert(resultComplexNumber)
        }

        checkLastResult.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {

                var resultComplexNumber: List<ResultComplexNumber>? = null
                withContext(Dispatchers.IO) {
                //    resultComplexNumber = db.resultComplexNumberDao().getLastResult()
                }
            }
        }

/*
        var resultOne = ""
        var resultTwo = ""

        db.resultComplexNumberDao().getAll().observe(this) { all ->
            val result = all.first().complexNumber
            resultOne = ""
            resultTwo = ""

            result.forEach {
                val key = 0
                if (key == 0) {
                    if (it == ' ')
                        resultOne += it
                } else {
                    resultTwo += it
                }
            }
        }

        checkLastResult.setOnClickListener {
            resultTextViewre.text = resultOne
            resultTextViewim.text = resultTwo
            lastresult.text = ("$resultOne $resultTwo")
        }
*/


        val buttonPhoto = findViewById<Button>(R.id.buttonPhoto)
        val buttonSend = findViewById<Button>(R.id.buttonSend)
        buttonSend.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, "$resultre $resultim")
            intent.type = "text/plain"
            val intentCreateСhooser = Intent.createChooser(intent, null)
            startActivity(intentCreateСhooser)
        }
        buttonPhoto.setOnClickListener {
            val intent = Intent()
            intent.action = MediaStore.ACTION_IMAGE_CAPTURE
            val intentCreateChooser = Intent.createChooser(intent, null)
            startActivityForResult(intentCreateChooser, REQUEST_IMAGE_CAPTURE)
            //startActivity(intentCreateChooser)
        }
    }
    companion object{
        const val REQUEST_IMAGE_CAPTURE = 1
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == REQUEST_IMAGE_CAPTURE)&&(resultCode == RESULT_OK)){
            val bitmap = data?.extras?.get("data") as Bitmap
            val imageView = findViewById<ImageView>(R.id.imageView)
            imageView.setImageBitmap(bitmap)
        }
    }
}