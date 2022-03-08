package classes

class MenuTableItem() : MenuItem(){
    override fun getName(): String {
        return "Table"
    }

    override fun run(system: SystemMy, index: Int?) {
        system.candidat.filter { it.name!="Random" }.forEach { print(it.name+"  ")
                                print(it.uchastok+"  ")
                                println(it.golosa)}

        DrawConsole().drawNextStage(3)
    }

    override fun getNumber(): Int {
        return 5
    }
}