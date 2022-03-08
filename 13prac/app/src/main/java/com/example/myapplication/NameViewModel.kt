package com.example.myapplication

import java.io.Serializable

class NameViewModel(name:Show):Serializable {
    val name = name.show!!.name
    val genre = name.show!!.genre
    val language = name.show!!.language
}