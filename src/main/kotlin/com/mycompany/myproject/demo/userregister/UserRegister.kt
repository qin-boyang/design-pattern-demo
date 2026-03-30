package com.mycompany.myproject.demo.userregister

enum class Listeners{ EMAIL_LISTENER, SMS_LISTENER, SCORE_LISTENER, SOME_OTHER_LISTENER }

sealed class UserRegisterListener {
    abstract fun onRegister(event: UserRegisterEvent)
    class EmailListener: UserRegisterListener() {
        override fun onRegister(event: UserRegisterEvent) { println("Sending email") }
    }
    class SmsListener: UserRegisterListener() {
        override fun onRegister(event: UserRegisterEvent) { println("Sending SMS") }
    }
    class ScoreListener: UserRegisterListener() {
        override fun onRegister(event: UserRegisterEvent) { println("Printing score") }
    }
}

data class UserRegisterEvent(val userName: String)

class UserService {
    fun register(userName: String) {
        println("Register Event")
        publishEvent(UserRegisterEvent(userName))
    }
    private fun publishEvent(event: UserRegisterEvent) {
        println("Publish Event")
        for (listener in Listeners.entries) {
            when (listener) {
                Listeners.EMAIL_LISTENER -> UserRegisterListener.EmailListener().onRegister(event)
                Listeners.SMS_LISTENER -> UserRegisterListener.SmsListener().onRegister(event)
                Listeners.SCORE_LISTENER -> UserRegisterListener.ScoreListener().onRegister(event)
                Listeners.SOME_OTHER_LISTENER -> println("Do nothing")
            }
        }
    }
}