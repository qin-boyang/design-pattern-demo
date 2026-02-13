package com.mycompany.myproject.demo.vehicle

interface DrivingStrategy {
    fun howToDrive() : String
}

class Car(val model: String, brand: String) : DrivingStrategy {
    override fun howToDrive() : String {
        return "$model is a car"
    }
}

class Truck(val brand: String, is4x4: Boolean) : DrivingStrategy {
    override fun howToDrive() : String {
        return "$brand is a truck"
    }
}

class SUV(val brand: String, year:  Int) : DrivingStrategy {
    override fun howToDrive() : String {
        return "$brand is a SUV"
    }
}

class DrivingSomething(var strategy: DrivingStrategy) {
    fun drive() : String {
        return strategy.howToDrive()
    }
}

