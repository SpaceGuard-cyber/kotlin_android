package com.example.myapplication

import android.app.Application
import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Entity
data class DataBaseShowMan (
    @ColumnInfo(name="nameShowman") val name: String,
    @ColumnInfo(name="genre") val genre: String,
    @ColumnInfo(name="language") val language: String
) {
    @PrimaryKey(autoGenerate = true) var uid:Int = 0
}

@Dao
interface ShowmanNameDao{
    @Query("SELECT * FROM DataBaseShowMan")
    fun getAll(): List<DataBaseShowMan>

    @Insert
    fun insertAll(nameShowman: List<DataBaseShowMan>)

    @Query("DELETE FROM DataBaseShowMan")
    fun deleteAll()
}

@Database(entities = [DataBaseShowMan::class], version = 1)
abstract class ShowmanNameDataBase: RoomDatabase() {
    abstract fun showmanNameDao(): ShowmanNameDao
}

class Names_Repository(val applicationContext: Context){
    val db = Room.databaseBuilder(applicationContext, ShowmanNameDataBase::class.java, "ShowmanName").build()

    fun getListShow():Show {
        val listDataBase = db.showmanNameDao().getAll()
        val listDataBaseForOutPutName = Show(
            newList = listDataBase.map {
                Find_Showman(
                    newName = it.name,
                    newGenre = listOf(it.genre),
                    newLanguage = it.language
                )
            })
        return listDataBaseForOutPutName
    }

    suspend fun saveToDB(){
        var listDataBase: List<DataBaseShowMan>? = null
        withContext(Dispatchers.IO) {
            listDataBase = db.showmanNameDao().getAll()
        }
        val listDataBaseForOutPutName = Show(
            newList = listDataBase!!.map {
                Find_Showman(
                    newName = it.name,
                    newGenre = listOf(it.genre),
                    newLanguage = it.language
                )
            })
        outputName.adapter = AdapterShowMan(listDataBaseForOutPutName, this@MainActivity)
        outputName.layoutManager = LinearLayoutManager(this@MainActivity)
    }
}