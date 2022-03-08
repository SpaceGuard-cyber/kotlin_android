package classes

import interfaces.*

class MenuForCandidate(system: SystemMy, vararg menuItems: MenuItem) :  SystemI,
    ElementsMenuI,
    CallElementsMenuI {

    override var system = system


    fun activeMenuManager() {
        managerListener?.choiceItem(elementsMenu,system)
    }

    var managerListener: ChoiceItemI? = null
    fun registerManager(listener: ChoiceItemI) {
        this.managerListener = listener
    }



    override fun callElementsMenu() {
        listenerMenuItems?.outputElementsMenu(*elementsMenu)
    }

    private var listenerMenuItems: OutputElementsMenuI? = null
    fun registerMenuItems(listener: OutputElementsMenuI) {
        this.listenerMenuItems = listener
    }

    override var elementsMenu: Array<MenuItem> = menuItems as Array<MenuItem>

}