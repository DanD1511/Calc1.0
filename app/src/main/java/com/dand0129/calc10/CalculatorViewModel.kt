package com.dand0129.calc10

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    /*val displayShow = remember { mutableStateOf("") }
    val number1 = remember { mutableStateOf("") }
    val number2 = remember { mutableStateOf("") }
    val resultOfOperations = remember { mutableStateOf(0.0) }
    val typeOfOperation = remember { mutableStateOf("") }
    val onOff = remember { mutableStateOf(false) }

     */
    private val _displayShow = MutableLiveData<String>("")
    val displayShow: LiveData<String> get() = _displayShow

    private val _number1 = MutableLiveData<String>("")
    val number1: LiveData<String> get() = _number1

    private val _number2 = MutableLiveData<String>("")
    val number2: LiveData<String> get() = _number2

    private val _resultOfOperations = MutableLiveData<String>("")
    val resultOfOperations: LiveData<String> get() = _resultOfOperations

    private val _typeOfOperation = MutableLiveData<String>("")
    val typeOfOperation: LiveData<String> get() = _typeOfOperation

    private val _onOff = MutableLiveData<Boolean>(false)
    val onOff: LiveData<Boolean> get() = _onOff

    fun setNumberOne(a: String) {
        _number1.value = a
    }

    fun setNumberTwo(a: String) {
        _number2.value = a
    }

    fun setTypeOfOperation(operator: String) {
        val currentTypeOperation = _typeOfOperation.value
        if (_number1.value!!.isEmpty()) return // Operar números negativos (To do)
        if (_number1.value!!.isNotEmpty() && currentTypeOperation!!.isEmpty()) {
            _typeOfOperation.value = operator
            _displayShow.value = ""
        } else {
            val temporalResult = calculateResult(
                _number1.value!!.toDouble(),
                _number2.value!!.toDouble(),
                currentTypeOperation!!
            )
            _number1.value = temporalResult.toString()
            _displayShow.value = ""
            _number2.value = ""
            _typeOfOperation.value = operator
        }

    }

    private fun calculateResult(number1: Double, number2: Double, operation: String): Double {
        //cambiar por un when
        return if (operation == "+") {
            number1 + number2
        } else if (operation == "-") {
            number1 - number2
        } else if (operation == "x") {
            number1 * number2
        } else {
            number1 / number2
        }
    }

    fun setDisplayShow(a: String) {
        _displayShow.value = a
    }

    fun enterNumber(digit: String) {
        val currentDisplayValue = _displayShow.value
        if (!_onOff.value!!) {

            if (_number1.value!!.isEmpty()) {
                _number1.value = digit
                _displayShow.value = digit
            } else if (_number1.value!!.isNotEmpty() && _typeOfOperation.value!!.isEmpty()) {
                val currentNumberOne = _number1.value
                if (!currentNumberOne!!.contains(".")) {
                    _number1.value = currentNumberOne + digit
                    _displayShow.value = currentDisplayValue + digit
                } else {
                    // TODO: manejar el punto repetido
                }
            } else if (_number1.value!!.isNotEmpty() && _typeOfOperation.value!!.isNotEmpty()
                && _number2.value!!.isEmpty()
            ) {
                _number2.value = digit
                _displayShow.value = digit
            } else {
                val currentNumberTwo = _number2.value
                if (!currentNumberTwo!!.contains(".")) {
                    _number2.value = currentNumberTwo + digit
                    _displayShow.value = currentDisplayValue + digit
                } else {
                    // TODO: manejar el punto repetido
                }
            }
        }
    }

    fun onResultClicked() {
        if(_number1.value!!.isNotEmpty() && _number2.value!!.isNotEmpty()
            && _typeOfOperation.value!!.isNotEmpty()){
            val result = calculateResult(_number1.value!!.toDouble(),_number2.value!!.toDouble(),
                _typeOfOperation.value!!)

            _number1.value = result.toString() //TODO: Que pasa si ya tengo un cálculo?
            _displayShow.value = result.toString()
            _typeOfOperation.value = ""
            _number2.value = ""
        }
    }

    fun onOffClicked(isOn: Boolean) {
        _onOff.value = isOn
    }
}