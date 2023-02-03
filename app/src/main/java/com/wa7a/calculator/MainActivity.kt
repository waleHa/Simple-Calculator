package com.wa7a.calculator

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.wa7a.calculator.Utils.randomColor
import com.wa7a.calculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var firstNumber: Double = 0.0
    var currentOp: Operation? = null
    var lightColor = "#${randomColor(ColorType.LIGHT)}"
    var darkColor = "#${randomColor(ColorType.DARK)}"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addCallBack()
        setColors()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.refresh) {
            lightColor = "#${randomColor(ColorType.LIGHT)}"
            darkColor = "#${randomColor(ColorType.DARK)}"
            setColors()
            return true
    }
    return super.onOptionsItemSelected(item)
}

fun setColors() {

    binding.textNumber.setTextColor(Color.parseColor(lightColor))
    binding.root.setBackgroundColor(Color.parseColor(darkColor))
    binding.scientificCalculatorForwardBtn.setBackgroundColor(Color.parseColor(lightColor))
    binding.scientificCalculatorForwardBtn.setTextColor(Color.parseColor(darkColor))

    val colorDrawable = ColorDrawable(Color.parseColor(lightColor))
    val actionBar = supportActionBar
    actionBar?.setBackgroundDrawable(colorDrawable)
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
    binding.scientificCalculatorForwardBtn.setOnClickListener {

        val browserIntent =
            Intent(Intent.ACTION_VIEW)
        browserIntent.data = Uri.parse("https://www.desmos.com/scientific")
        //2:Intent.ACTION_DIAL
        startActivity(browserIntent)
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
}