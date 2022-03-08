package classes

class MenuExitItem() : MenuItem(){


    override fun getName(): String {
        return "Exit"
    }

    override fun run(system: SystemMy, index: Int?) {
        system.end = true
    }

    override fun getNumber(): Int {
        return 6
    }
}