package classes

class MenuDeliteItem() : MenuItem() {
    override fun getName(): String {
        return "Delite record"
    }

    override fun run(system: SystemMy, index: Int?) {
        println("Введите имя")
        val equalsStr = readLine()
        if(equalsStr!=null) {
            val index = system.candidat.indexOfFirst { it.name==equalsStr }
            if(system.candidat[index].name!="Random"){
                system.candidat[index].name = "Random"
                system.candidat[index].uchastok = "Random"
                println("Введите голоса свое")
                system.candidat[index].golosa = -1
                DrawConsole().drawNextStage(3)
            }
        }
    }

    override fun getNumber(): Int {
        return 7
    }
}