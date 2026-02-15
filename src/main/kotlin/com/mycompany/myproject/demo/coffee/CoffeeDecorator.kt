package com.mycompany.myproject.demo.coffee

interface Coffee {
    fun getCost(): Double
    fun getDescription(): String
}
class SimpleCoffee : Coffee {
    override fun getCost(): Double = 10.0
    override fun getDescription(): String = "Simple Coffee"
}
class SugarDecorator(private val coffee: Coffee): Coffee by coffee {
    override fun getCost(): Double = coffee.getCost() + 2.0
    override fun getDescription(): String = "${coffee.getDescription()}, Sugar"

}
class MilkDecorator(private val coffee: Coffee): Coffee by coffee{
    override fun getCost(): Double = coffee.getCost() + 1.0
    override fun getDescription(): String = "${coffee.getDescription()}, Milk"
}