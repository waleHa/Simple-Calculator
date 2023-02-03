package  com.wa7a.calculator.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.wa7a.calculator.utils.Constants
import com.wa7a.calculator.ui.InputInteractionListener
import com.wa7a.calculator.R
import com.wa7a.calculator.data.Input
import com.wa7a.calculator.data.InputType
import com.wa7a.calculator.databinding.ItemInputBinding
import com.wa7a.calculator.databinding.ItemInputWideBinding
import com.wa7a.calculator.utils.Constants.CLEAR
import com.wa7a.calculator.utils.Constants.DIVIDE
import com.wa7a.calculator.utils.Constants.EQUAL
import com.wa7a.calculator.utils.Constants.MINUS
import com.wa7a.calculator.utils.Constants.MODE
import com.wa7a.calculator.utils.Constants.MULT
import com.wa7a.calculator.utils.Constants.PLUS
import com.wa7a.calculator.utils.Constants.REMOVE

class InputAdapter(val list: List<Input>, val listener: InputInteractionListener) :
    RecyclerView.Adapter<InputAdapter.BaseViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {


        val view = when (viewType) {
            VIEW_TYPE_INPUT -> LayoutInflater.from(parent.context)
                .inflate(R.layout.item_input, parent, false)
            VIEW_TYPE_WIDE_INPUT -> LayoutInflater.from(parent.context)
                .inflate(R.layout.item_input_wide, parent, false)
            else -> LayoutInflater.from(parent.context)
                .inflate(R.layout.item_input, parent, false)
        }
        return InputViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        lightColor = "#${Constants.LIGHT_COLOR}"
        darkColor = "#${Constants.DARK_COLOR}"
        veryLightColor = "#${Constants.VERY_LIGHT_COLOR}"
        val currentInput = list[position]
        val bind: ViewBinding
        when (holder) {
            is InputViewHolder -> bindInit(holder.binding.textView, currentInput)
            is InputWideViewHolder -> bindInit(holder.binding.textView, currentInput)
        }
    }

    fun bindInit(textView: TextView, currentInput: Input) {
        textView.text = currentInput.value
        if (currentInput.type == InputType.NUMBER)
            textView.setBackgroundColor(Color.parseColor(veryLightColor))
        else if (currentInput.type == InputType.OPERATION)
            textView.setBackgroundColor(Color.parseColor(lightColor))

        textView.setOnClickListener {
            when (currentInput.type == InputType.NUMBER) {
                true -> listener.onClickNumber(currentInput.value)
                false -> when (currentInput.value) {
                    CLEAR -> listener.onClickClear()
                    REMOVE -> listener.onClickRemove()
                    PLUS -> listener.onClickPlus()
                    MULT -> listener.onClickMultiply()
                    MINUS -> listener.onClickMinus()
                    DIVIDE -> listener.onClickDivide()
                    MODE -> listener.onClickMode()
                    EQUAL -> listener.onClickEqual()
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == list.size - 1) {
            VIEW_TYPE_WIDE_INPUT
        } else {
            VIEW_TYPE_INPUT
        }
    }

    override fun getItemCount() = list.size

    abstract class BaseViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem)

    class InputViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
        val binding = ItemInputBinding.bind(viewItem)
    }

    class InputWideViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
        val binding = ItemInputWideBinding.bind(viewItem)
    }

    companion object {
        lateinit var lightColor: String
        lateinit var veryLightColor: String
        lateinit var darkColor: String
        const val VIEW_TYPE_INPUT = 11
        const val VIEW_TYPE_WIDE_INPUT = 12
    }

    fun newColors(light: String, veryLight: String, dark: String) {
        val diffResult = DiffUtil.calculateDiff(InputDiffUtil(lightColor, light,list.size))
        lightColor = light
        veryLightColor = veryLight
        darkColor = dark
        diffResult.dispatchUpdatesTo(this)
    }
}
