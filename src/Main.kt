import kotlin.random.Random

fun main() {
    val cities = listOf(
        "Санкт-Петербург", "Москва", "Новосибирск", "Екатеринбург", "Казань",
        "Нижний Новгород", "Челябинск", "Саратов", "Омск", "Ростов-на-Дону",
        "Уфа", "Красноярск", "Пермь", "Воронеж", "Камчатка"
    )

    while (true) {
        println("Введите 'EXIT' для завершения работы или 'START' для составления плана поезда:")
        when (readLine()?.uppercase()) {
            "EXIT" -> break
            "START" -> {
                val deric = createDirection(cities)
                println("Создано направление: $deric")

                var passagiry = sellTickets()
                println("Количество пассажиров, купивших билеты: $passagiry")

                val train = formTrain(passagiry)
                println("Сформирован поезд: ${train.size} вагонов.")
                train.forEachIndexed { index, mesta ->
                    println("Вагон ${index + 1}: Вместимость - $mesta, Пассажиров - ${minOf(passagiry, mesta)}")
                    passagiry -= minOf(passagiry, mesta)
                }

                println("Поезд $deric, состоящий из ${train.size} вагонов, отправлен.")
            }
            else -> println("Неверный ввод, пожалуйста, попробуйте снова.")
        }
    }
}

fun createDirection(cities: List<String>): String {
    val startCity = cities.random()
    var endCity: String
    do {
        endCity = cities.random()
    } while (endCity == startCity)
    return "$startCity - $endCity"
}

fun sellTickets(): Int {
    return Random.nextInt(5, 201)
}

fun formTrain(passagiry: Int): List<Int> {
    val train = mutableListOf<Int>()
    var remainingPpassagiry = passagiry
    while (remainingPpassagiry > 0) {
        val mesta = Random.nextInt(5, 25)
        train.add(mesta)
        remainingPpassagiry -= mesta
    }
    return train
}
