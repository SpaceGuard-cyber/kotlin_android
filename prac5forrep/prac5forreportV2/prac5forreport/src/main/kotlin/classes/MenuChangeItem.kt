package classes

class MenuChangeItem() : MenuItem(){
    override fun getName(): String {
        return "Change record"
    }

    override fun run(system: SystemMy, index: Int?) {
        println("Введите имя")
        val equalsStr = readLine()
        if(equalsStr!=null) {
            val index = system.candidat.indexOfFirst { it.name==equalsStr }
            if(system.candidat[index].name!="Random"){
                println("Введите новое имя")
                val str = readLine()
                if(str!=null) {
                    system.candidat[index].name = str
                    println("Введите участок ")
                    val str = readLine()
                    if(str!=null) {
                        system.candidat[index].uchastok = str
                        println("Введите голоса ")
                        val str = readLine()?.toIntOrNull()
                        system.candidat[index].golosa = str!!
                        DrawConsole().drawNextStage(3)
                    }
                }
            }
        }
    }

    override fun getNumber(): Int {
        return 2
    }
}