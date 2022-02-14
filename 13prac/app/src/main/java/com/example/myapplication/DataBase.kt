package com.example.myapplication

import androidx.room.*

@Entity
data class DataBaseShowMan (
    @ColumnInfo(name="nameShowman") val name: String,
    @ColumnInfo(name="genre") val genre: String,
    @ColumnInfo(name="language") val language: String){
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