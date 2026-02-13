package com.mycompany.myproject.demo.tax

class TaxCalculator(private val strategies: List<TaxStrategy>) {
    fun calculateTax(amount: Double) : Double {
        var tax = 0.0
        for (strategy in strategies) {
            tax += strategy.calc(amount)
        }
        return tax
    }
}

interface TaxStrategy {
    fun calc(amount: Double): Double
}
class GeneralTaxStrategy(private val taxRate: Double): TaxStrategy {
    override fun calc(amount: Double): Double =
        amount * taxRate / 100
}
class ImportTaxStrategy(private val importTaxRate: Double): TaxStrategy {
    override fun calc(amount: Double): Double =
        amount * importTaxRate / 100
}
class ExemptTaxStrategy: TaxStrategy {
    override fun calc(amount: Double): Double =
        0.0
}

data object TaxFactory {
    fun create(category: String, isImported: Boolean): TaxCalculator {
        val strategies = mutableListOf<TaxStrategy>()
        if (isImported) {
            strategies.add(ImportTaxStrategy(5.0))
        }
        when (category) {
            "Book", "Food" -> strategies.add(ExemptTaxStrategy())
            "General" -> strategies.add(GeneralTaxStrategy(10.0))
            else -> throw IllegalArgumentException("Invalid category")
        }
        return TaxCalculator(strategies)
    }
}