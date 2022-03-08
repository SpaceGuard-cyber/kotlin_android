package interfaces

import classes.SystemMy

interface MenuItemRunI {
    fun run(system : SystemMy, index: Int? = -1)
}