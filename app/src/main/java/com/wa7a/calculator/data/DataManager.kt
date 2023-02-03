package com.wa7a.calculator.data

import com.wa7a.calculator.data.Input
import com.wa7a.calculator.data.InputType
import com.wa7a.calculator.utils.Constants.CLEAR
import com.wa7a.calculator.utils.Constants.DIVIDE
import com.wa7a.calculator.utils.Constants.EQUAL
import com.wa7a.calculator.utils.Constants.MINUS
import com.wa7a.calculator.utils.Constants.MODE
import com.wa7a.calculator.utils.Constants.MULT
import com.wa7a.calculator.utils.Constants.PERIOD
import com.wa7a.calculator.utils.Constants.PLUS
import com.wa7a.calculator.utils.Constants.REMOVE

class DataManager {
    companion object {
        val list: List<Input> = listOf(

            Input("Plus", PLUS, InputType.OPERATION),
            Input("Minus", MINUS, InputType.OPERATION),
            Input("Mult", MULT, InputType.OPERATION),
            Input("Divide", DIVIDE, InputType.OPERATION),
            Input("7", "7", InputType.NUMBER),
            Input("8", "8", InputType.NUMBER),
            Input("9", "9", InputType.NUMBER),
            Input("Clear", CLEAR, InputType.OPERATION),
            Input("4", "4", InputType.NUMBER),
            Input("5", "5", InputType.NUMBER),
            Input("6", "6", InputType.NUMBER),
            Input("Remove", REMOVE, InputType.OPERATION),
            Input("1", "1", InputType.NUMBER),
            Input("2", "2", InputType.NUMBER),
            Input("3", "3", InputType.NUMBER),
            Input("Mode", MODE, InputType.OPERATION),
            Input("0", "0", InputType.NUMBER),
            Input("Period", PERIOD, InputType.NUMBER),
            Input("Equal", EQUAL, InputType.OPERATION)
        )

    }


}