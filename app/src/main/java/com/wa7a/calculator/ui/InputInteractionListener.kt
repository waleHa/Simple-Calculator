package  com.wa7a.calculator.ui

interface InputInteractionListener {
    fun onClickNumber(value: String)
    fun onClickClear()
    fun onClickRemove()
    fun onClickPlus()
    fun onClickMultiply()
    fun onClickMinus()
    fun onClickDivide()
    fun onClickMode()
    fun onClickEqual()
}