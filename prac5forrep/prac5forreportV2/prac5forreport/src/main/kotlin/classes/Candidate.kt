package classes

import interfaces.NameI

data class Candidate(val n : String) : NameI {
    override var name: String = n
    var uchastok: String = n
    var golosa: Int = 0
}