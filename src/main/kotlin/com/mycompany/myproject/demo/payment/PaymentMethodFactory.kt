package com.mycompany.myproject.demo.payment

enum class PaymentMethodType {
    Cash ,
    Card,
    UNKNOWN
}

sealed class PaymentMethod {
    data object Cash : PaymentMethod()
    data class Card(val number: String) : PaymentMethod()

    // Factory method inside the sealed class itself
    companion object {
        fun create(type: PaymentMethodType, cardnumber: String? = ""): PaymentMethod = when(type) {
            PaymentMethodType.Cash -> Cash
            PaymentMethodType.Card -> Card(cardnumber.orEmpty())
            PaymentMethodType.UNKNOWN -> throw Exception()
        }
    }

    fun calculatePaymentFee() : Double {
        return when(this) {
            is Cash -> 0.0
            is Card -> 0.5
        }
    }
}