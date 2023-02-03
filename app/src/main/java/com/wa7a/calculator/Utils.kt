package com.wa7a.calculator

import kotlin.random.Random

object Utils {
    fun randomColor(colorType: ColorType): String {
        var color = ""
        val listLightColors = listOf(
            "A", "B", "C", "D", "A", "5", "6", "7", "8", "A", "A", "B", "C", "D", "E", "F"
        )
        val listDarkColors = listOf(
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "4", "0", "1", "2", "3", "4"
        )
        for (i in 0..5) {
            val random0 = Random.nextInt(0, 14)
            when (colorType) {
                ColorType.DARK -> color += listDarkColors[random0]
                ColorType.LIGHT ->  color += listLightColors[random0]
            }

        }
        return color
    }

}
enum class ColorType {
    DARK, LIGHT
}