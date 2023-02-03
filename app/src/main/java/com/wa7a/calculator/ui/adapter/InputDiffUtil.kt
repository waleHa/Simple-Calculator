package  com.wa7a.calculator.ui.adapter

import androidx.recyclerview.widget.DiffUtil

class InputDiffUtil(val oldColor: String,val newColor: String,val length:Int): DiffUtil.Callback() {
    override fun getOldListSize(): Int = length

    override fun getNewListSize(): Int = length

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldColor == newColor
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }

}