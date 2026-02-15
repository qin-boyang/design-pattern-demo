package com.mycompany.myproject.demo.dsl

const val PERSON_JSON = """
{
    "name": "John",
    "age": 30,
    "address": {
        "street": "123 Main St",
        "city": "New York",
        "state": "NY",
        "zip": "10001"
    },
    "jobs": [
        {
            "title": "Software Engineer",
            "company": "Google"
        },
        {
            "title": "Data Scientist",
            "company": "Amazon"
        }
    ]
}
"""
// 1. Define a Marker to prevent scope leaking
@DslMarker
annotation class PersonDslMarker

@PersonDslMarker
class Person {
    var name: String = ""
    var age: Int = 0
    var address: Address? = null
    val jobs: MutableList<Job> = mutableListOf()

    @PersonDslMarker
    class Address {
        var street: String = ""
        var city: String = ""
        var state: String = ""
        var zip: String = ""
    }
    fun address(init: Address.() -> Unit) {
        val addressInstance = Address()
        addressInstance.init()
        address = addressInstance
    }

    @PersonDslMarker
    class Job {
        var title: String = ""
        var company: String = ""
    }
    fun job(init: Job.() -> Unit) {
        val jobInstance = Job()
        jobInstance.init()
        jobs.add(jobInstance)
    }
}
fun person(init: Person.() -> Unit): Person {
    val personInstance = Person()
    personInstance.init()
    return personInstance
}