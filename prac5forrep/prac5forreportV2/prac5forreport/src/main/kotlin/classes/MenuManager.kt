package classes

import interfaces.ChoiceItemI

class MenuManager() : ChoiceItemI {


    override fun choiceItem(menuItems: Array<MenuItem>, system: SystemMy) {
        for(i in menuItems){
            println(i.getName())
        }
        print("\nВыберите нужный вам номер меню : ")
        val numberMenu = readLine()?.toIntOrNull()
        DrawConsole().drawNextStage(3)
        if(numberMenu==null || numberMenu<1 || numberMenu>menuItems.size) println("Не правильно введен номер")
        else{
            for(i in menuItems.indices){
                if(numberMenu==menuItems[i].getNumber()){
                    menuItems[i].run(system)
                }
            }
        }
        DrawConsole().drawNextStage(3)
    }
}