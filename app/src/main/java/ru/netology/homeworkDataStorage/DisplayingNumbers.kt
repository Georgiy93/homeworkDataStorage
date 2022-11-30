package ru.netology.homeworkDataStorage


fun DisplayingNumbers(number: Long): String {
    var numberToText = number.toString()
    if (number >= 1000) {

        numberToText = "%.1f".format(
            (number.toDouble() / 1000)
        ) + "K "
    }
    if (number >= 10_000) {

        numberToText = "%.0f".format(
            (number.toDouble() / 1000)
        ) + "K "
    }
    if (number >= 1000_000) {
        numberToText = "%.1f".format(
            (number.toDouble() / 1000_000)
        ) + "МЛН "

    }
    if (number >= 1000_000_000) {
        numberToText = "%.1f".format(
            (number.toDouble() / 1000_000_000)
        ) + "МЛД"

    }
    return numberToText
}