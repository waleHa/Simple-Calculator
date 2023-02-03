package com.wa7a.calculator.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.wa7a.calculator.R
import com.wa7a.calculator.data.DataManager
import com.wa7a.calculator.data.Operation
import com.wa7a.calculator.ui.adapter.InputAdapter
import com.wa7a.calculator.utils.ColorType
import com.wa7a.calculator.databinding.ActivityMainBinding
import com.wa7a.calculator.utils.Constants.DARK_COLOR
import com.wa7a.calculator.utils.Constants.LIGHT_COLOR
import com.wa7a.calculator.utils.Constants.VERY_LIGHT_COLOR
import com.wa7a.calculator.utils.Constants.randomColor


class MainActivity() : AppCompatActivity(), InputInteractionListener {

    var firstNumber: Double = 0.0
    var currentOp: Operation? = null
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: InputAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addCallBack()
        setup()
    }

    private fun setup() {
        adapter = InputAdapter(DataManager.list, this)
        binding.recyclerView1.adapter = adapter
        setColors()
    }

    private fun setColors() {
        binding.textNumber.setTextColor(Color.parseColor("#$LIGHT_COLOR"))
        binding.root.setBackgroundColor(Color.parseColor("#$DARK_COLOR"))
        binding.scientificCalculatorForwardBtn.setBackgroundColor(Color.parseColor("#$VERY_LIGHT_COLOR"))
        binding.scientificCalculatorForwardBtn.setTextColor(Color.parseColor("#$DARK_COLOR"))

        val colorDrawable = ColorDrawable(Color.parseColor("#$LIGHT_COLOR"))
        val actionBar = supportActionBar
        actionBar?.setBackgroundDrawable(colorDrawable)
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


    fun addCallBack() {
        binding.scientificCalculatorForwardBtn.setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse("https://www.desmos.com/scientific")
            //2:Intent.ACTION_DIAL
            startActivity(browserIntent)
        }
    }


    override fun onClickNumber(value: String) {
        val oldDigit = binding.textNumber.text.toString()
        val newTextNumber = oldDigit + value
        binding.textNumber.text = newTextNumber
    }

    override fun onClickClear() {
        clearInput()
    }

    override fun onClickRemove() {
        binding.textNumber.text =
            binding.textNumber.text.subSequence(0, binding.textNumber.text.length - 1)
    }

    override fun onClickPlus() {
        prepareOperation(Operation.Plus)
    }

    override fun onClickMultiply() {
        prepareOperation(Operation.Mul)
    }

    override fun onClickMinus() {
        prepareOperation(Operation.Minus)
    }

    override fun onClickDivide() {
        prepareOperation(Operation.Div)
    }

    override fun onClickMode() {
        prepareOperation(Operation.Mode)
    }

    override fun onClickEqual() {
        val result = doOp()
        binding.textNumber.text = result.toString()
    }

    private fun clearInput() {
        binding.textNumber.text = ""
    }

    fun prepareOperation(op: Operation) {
        currentOp = op
        firstNumber = binding.textNumber.text.toString().toDouble()
        clearInput()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.refresh) {
            LIGHT_COLOR = "${randomColor(ColorType.LIGHT)}"
            VERY_LIGHT_COLOR = "${randomColor(ColorType.VERY_LIGHT)}"
            DARK_COLOR = "${randomColor(ColorType.DARK)}"

            adapter.newColors(LIGHT_COLOR,VERY_LIGHT_COLOR,DARK_COLOR)
            setColors()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}