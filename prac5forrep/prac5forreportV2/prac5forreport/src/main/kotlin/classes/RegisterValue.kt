package classes

import interfaces.RegisterNameI

class RegisterValue() : RegisterNameI {
    override fun registerName(system: classes.SystemMy) {
        print("Введите свой номер: ")
        val numberEntrepreneur = readLine()?.toIntOrNull()
        if(numberEntrepreneur!=null) {
            print("Введите ваше имя: ")
            when (val fakeName = readLine().toString()) {
                "" -> println("\nВы не ввели имя")
                "Random" -> println("\nЭто имя не доступно")
                else -> {
                    system.candidat[numberEntrepreneur].name = fakeName
                }
            }
        }
    }
}