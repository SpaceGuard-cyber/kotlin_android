package classes

class DrawConsole {

    fun drawNextStage(count : Int = 25){
        for(i in 0..count) println()
    }

    fun drawLine(s : String = "_", count : Int = 175){
        for(i in 0..count){
            print(s)
        }
    }
}