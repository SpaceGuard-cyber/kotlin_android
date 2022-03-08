package classes

import interfaces.NameI

class SystemMy(n: String) : NameI {
    val candidat = Array(100){Candidate("Random")}
    override val name: String = n
    var end = false

        var counter = 0

}