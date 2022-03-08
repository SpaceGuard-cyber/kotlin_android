package classes

class MenuFindItem() : MenuItem(){
    override fun getName(): String {
        return "Find record"
    }

    override fun run(system: SystemMy, index: Int?) {
        println("Введите имя")
        var strName = readLine()
        if(strName!=null) {
            for(l in system.candidat.filter { it.name.contains(strName) }){
                print(l.name+"  ")
                print(l.uchastok+"  ")
                println(l.golosa)
            }
        }
        DrawConsole().drawNextStage(3)
    }

    override fun getNumber(): Int {
        return 3
    }
}