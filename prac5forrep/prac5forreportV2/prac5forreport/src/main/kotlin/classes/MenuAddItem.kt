package classes

class MenuAddItem() : MenuItem() {
    override fun getName(): String {
        return "Add record"
    }

    override fun run(system: SystemMy, index: Int?) {
        println("Введите имя ")
        val str = readLine()
        if(str!=null) {
            system.candidat[system.counter].name = str
            println("Введите участок ")
            val str = readLine()
            if(str!=null) {
                system.candidat[system.counter].uchastok = str
                println("Введите голоса ")
                val str = readLine()?.toIntOrNull()
                system.candidat[system.counter].golosa = str!!
                system.counter++
                DrawConsole().drawNextStage(3)
            }
        }
    }


    override fun getNumber(): Int {
        return 1
    }
}