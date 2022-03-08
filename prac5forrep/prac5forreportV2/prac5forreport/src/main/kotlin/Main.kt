import classes.*


fun main(){
    val system = SystemMy("MetaGlobal")
    val menu = MenuForCandidate(system, MenuAddItem(), MenuChangeItem(), MenuFindItem(),
        MenuSortItem(), MenuTableItem(), MenuExitItem(), MenuDeliteItem())
    menu.registerManager(MenuManager())
    DrawConsole().drawNextStage()
    while(!system.end) {
        menu.callElementsMenu()
        menu.activeMenuManager()
    }
}








