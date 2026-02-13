package com.mycompany.myproject.demo.membership

import java.math.BigDecimal
import java.math.RoundingMode

// 1. 定義策略接口
interface DiscountStrategy {
    fun calculateDiscount(currentAmount: BigDecimal): BigDecimal
}

// 2. 會員折扣策略：百分比制
class MembershipDiscount(private val rate: BigDecimal) : DiscountStrategy {
    override fun calculateDiscount(currentAmount: BigDecimal): BigDecimal {
        return currentAmount.multiply(rate).setScale(2, RoundingMode.HALF_UP)
    }
}

// 3. 滿額扣減策略：固定金額
class ThresholdDiscount(
    private val threshold: BigDecimal,
    private val rebate: BigDecimal
) : DiscountStrategy {
    override fun calculateDiscount(currentAmount: BigDecimal): BigDecimal {
        return if (currentAmount >= threshold) rebate else BigDecimal.ZERO
    }
}

// 4. 計算器引擎：支持鏈式策略 (Sequential)
class DiscountCalculator(private val strategies: List<DiscountStrategy>) {

    fun calculate(initialAmount: Double): Double {
        val amount = BigDecimal.valueOf(initialAmount)

        // 1. 初始化總折扣為 0
        var totalDiscount = BigDecimal.ZERO

        // 2. 遍歷每一個折扣策略，並把折扣金額累加起來
        for (strategy in strategies) {
            val discountAmount = strategy.calculateDiscount(amount)
            totalDiscount = totalDiscount.add(discountAmount)
        }

        val finalPrice = amount.subtract(totalDiscount).max(BigDecimal.ZERO)
        return finalPrice.toDouble()
    }
}

// 5. 工廠：將業務類型映射到策略組合
enum class MembershipType { REGULAR, VIP, SUPER_VIP }

object DiscountFactory {
    fun createFor(type: MembershipType): DiscountCalculator {
        val rate = when (type) {
            MembershipType.REGULAR -> "0.0"
            MembershipType.VIP -> "0.1"
            MembershipType.SUPER_VIP -> "0.2"
        }

        return DiscountCalculator(listOf(
            MembershipDiscount(BigDecimal(rate)),
            ThresholdDiscount(BigDecimal("1000.0"), BigDecimal("50.0"))
        ))
    }
}
