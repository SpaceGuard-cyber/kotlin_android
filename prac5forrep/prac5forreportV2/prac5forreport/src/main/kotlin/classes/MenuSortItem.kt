package classes

class MenuSortItem() : MenuItem(){
    override fun getName(): String {
        return "Sort record"
    }

    override fun run(system: SystemMy, index: Int?) {
        println("Выберете что вы будете сортировать")
        println("1. Голоса")
        var choiceSort = readLine()?.toIntOrNull()
        if(choiceSort!=null){
            var list = system.candidat.toList()
            for (l in list.filter { it.name!="Random" }.sortedBy { it.golosa }){
                print(l.name+"  ")
                print(l.uchastok+"  ")
                println(l.golosa)
            }
            //print(system.candidat.filter { it.name != "Random" }.forEach {  })
        }
    }

    override fun getNumber(): Int {
        return 4
    }
}