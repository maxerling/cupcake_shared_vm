package com.example.cupcake

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class OrderViewModel : ViewModel( ){
    private val CUPCAKE_PRICE: Double = 2.00
    private val ON_SAME_DAY_DELIVERY: Double = 3.00
    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity
    val dateOptions = getPickUpOptions()

    private val _flavor = MutableLiveData<String>()
    val flavor: LiveData<String> = _flavor

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    private val _price = MutableLiveData<Double>()
    val price: LiveData<Double> = _price

    init {
        resetOrder()
    }

    fun setQuantity(numberCupcakes: Int) {
        _quantity.value = numberCupcakes
    }

    fun setFlavor(desiredFlavor: String) {
        _flavor.value = desiredFlavor
    }

    fun setDate(pickupDate: String) {
        _date.value = pickupDate
    }

    fun setPrice(quantity: Int) {
        _price.value = CUPCAKE_PRICE * quantity
    }

    fun hasNoFlavorSet(): Boolean {
        return _flavor.value.isNullOrEmpty()
    }

    private fun getPickUpOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()

        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE,1)
        }
        return options
    }

    private fun resetOrder() {
        _quantity.value = 0
        _flavor.value = ""
        _date.value = dateOptions[0]
        _price.value = 0.0

    }
    fun egg() {}
}