package com.wa7a.calculator

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.wa7a.calculator.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var firstNumber: Double = 0.0
    var currentOp: Operation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addCallBack()
        binding.textNumber.setTextColor(Color.parseColor("#${randomCreater()}"))
    }

    fun addCallBack() {
        binding.operationClear.setOnClickListener {
            clearInput()
        }

        binding.operationMinus.setOnClickListener {
            prepareOperation(Operation.Minus)
        }

        binding.operationPlus.setOnClickListener {
            prepareOperation(Operation.Plus)
        }

        binding.operationDivide.setOnClickListener {
            prepareOperation(Operation.Div)
        }

        binding.operationMultiply.setOnClickListener {
            prepareOperation(Operation.Mul)

        }
        binding.operationEqual.setOnClickListener {
            val result = doOp()
            binding.textNumber.text = result.toString()
        }
        binding.operationX.setOnClickListener {
            binding.textNumber.text =
                binding.textNumber.text.subSequence(0, binding.textNumber.text.length - 1)
        }
        binding.operationMode.setOnClickListener {
            prepareOperation(Operation.Mode)
        }

    }

    private fun doOp(): Double {
        val secondNumber = binding.textNumber.text.toString().toDouble()
        return when (currentOp) {
            Operation.Plus -> firstNumber + secondNumber
            Operation.Minus -> firstNumber - secondNumber
            Operation.Mul -> firstNumber * secondNumber
            Operation.Div -> firstNumber / secondNumber
            Operation.Mode -> firstNumber % secondNumber
            null -> 0.0

        }
    }

    private fun clearInput() {
        binding.textNumber.text = ""
    }

    fun prepareOperation(op: Operation) {
        currentOp = op
        firstNumber = binding.textNumber.text.toString().toDouble()
        clearInput()
    }

    fun onClickNumber(v: View) {
        val newDigit = (v as Button).text.toString()
        val oldDigit = binding.textNumber.text.toString()
        val newTextNumber = oldDigit + newDigit
        binding.textNumber.text = newTextNumber
    }

    fun randomCreater(): String {
        var color = ""
        val list = listOf(
            "A", "B", "C", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"
        )
        for (i in 0..5) {
            val random0 = Random.nextInt(0, 14)
            color += list[random0]
        }
        return color
    }
}