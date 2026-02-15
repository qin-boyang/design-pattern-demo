package com.mycompany.myproject.demo.dsl

import kotlin.test.Test

class PersonDSLTest {
    @Test
    fun testPersonDSL() {

        val myPerson = person {
            name = "John"
            age = 30
            address {
                street = "123 Main St"
                city = "New York"
                state = "NY"
                zip = "10001"
            }
            job {
                title = "Software Engineer"
                company = "Google"
            }
            job {
                title = "Data Scientist"
                company = "Amazon"
            }
        }

        println("${myPerson.name}, ${myPerson.age}")
        println("${myPerson.address?.street}, ${myPerson.address?.city}, ${myPerson.address?.state}, ${myPerson.address?.zip}")
        myPerson.jobs.forEach {
            println("${it.title}, ${it.company}")
        }
    }
}