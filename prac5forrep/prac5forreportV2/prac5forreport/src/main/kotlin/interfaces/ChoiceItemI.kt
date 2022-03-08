package interfaces

import classes.MenuItem
import classes.SystemMy


interface ChoiceItemI {
    fun choiceItem(menuItems : Array<MenuItem>, system: SystemMy)
}