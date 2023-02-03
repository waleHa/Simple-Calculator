package  com.wa7a.calculator.utils

import kotlin.random.Random

object Constants {
    var LIGHT_COLOR = randomColor(ColorType.LIGHT)
    var VERY_LIGHT_COLOR = randomColor(ColorType.VERY_LIGHT)
    var DARK_COLOR = randomColor(ColorType.DARK)
    const val CLEAR = "C"
    const val REMOVE = "X"
    const val PLUS = "+"
    const val MULT = "*"
    const val MINUS = "-"
    const val DIVIDE = "/"
    const val MODE = "%"
    const val PERIOD = "."
    const val EQUAL = "="


    fun randomColor(colorType: ColorType): String {
        var color = ""
        val listLightColors = listOf(
            "A", "B", "C", "D", "A", "5", "6", "7", "8", "A", "A", "B", "C", "D", "E", "F"
        )
        val listVeryLightColors = listOf(
            "A", "B", "C", "D", "C", "D", "E", "F"
        )
        val listDarkColors = listOf(
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "4", "0", "1", "2", "3", "4"
        )
        for (i in 0..5) {
            val random :Int
            when (colorType) {
                ColorType.DARK -> {
                     random = Random.nextInt(1, listDarkColors.size)
                    color += listDarkColors[random]
                }
                ColorType.LIGHT -> {
                    random = Random.nextInt(1, listLightColors.size)
                    color += listLightColors[random]
                }
                ColorType.VERY_LIGHT -> {
                    random = Random.nextInt(1, listVeryLightColors.size)
                    color += listVeryLightColors[random]
                }
            }

        }
        return color
    }

}
enum class ColorType {
    DARK, LIGHT,VERY_LIGHT
}