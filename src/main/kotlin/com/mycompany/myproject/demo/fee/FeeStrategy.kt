package com.mycompany.myproject.demo.fee

enum class PaymentType {
    CREDIT_CARD,
    BANK_TRANSFER,
    CASH
}

interface FeeStrategy {
    fun calculateFee(amount: Double): Double
}

class CreditCardFeeStrategy(private val percentage: Double): FeeStrategy{
    override fun calculateFee(amount: Double): Double =
        amount * percentage / 100


}

class BankTransferFeeStrategy(private val fixedFee: Double): FeeStrategy {
    override fun calculateFee(amount: Double): Double =
        fixedFee

}

class CashFeeStrategy: FeeStrategy {
    override fun calculateFee(amount: Double): Double =
         0.0

}

data object FeeFactory {
    fun create(type: PaymentType): FeeStrategy {
        return when (type) {
            PaymentType.CREDIT_CARD -> CreditCardFeeStrategy(3.0)
            PaymentType.BANK_TRANSFER -> BankTransferFeeStrategy(5.0)
            PaymentType.CASH -> CashFeeStrategy()
        }
    }
}