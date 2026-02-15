package com.mycompany.myproject.demo.dsl

const val COUNTRY_JSON = """
    country {
        name = "USA"
        population = 300000000
        capital {
            name = "Washington"
            latitude = 38.8951
            longitude = -77.0364
        }
        states {
            state {
                name = "New York"
                cities {
                    city {
                        name = "New York"
                        population = 8000000
                    }
                    city {
                        name = "Buffalo"
                        population = 600000
                    }
                }
            }
            state {
                name = "California"
                cities {
                    city {
                        name = "Los Angeles"
                        population = 4000000
                    }
                    city {
                        name = "San Francisco"
                        population = 800000
                    }
                }
            }
        }
    }
"""

annotation class CountryDslMarker

@CountryDslMarker
class Country {
    var name: String = ""
    var population: Int = 0
    var capital: Capital? = null
    val states: MutableList<State> = mutableListOf()

    @CountryDslMarker
    class Capital {
        var name: String = ""
        var latitude: Double = 0.0
        var longitude: Double = 0.0
    }
    fun capital(block: Capital.() -> Unit) {
        capital = Capital().apply(block)
    }

    @CountryDslMarker
    class State() {
        var name: String = ""
        val cities: MutableList<City> = mutableListOf()

        class City {
            var name: String = ""
            var population: Int = 0
        }
        fun city(block: City.() -> Unit) {
            cities.add(City().apply(block))
        }
    }
    fun state(block: State.() -> Unit) {
        states.add(State().apply(block))
    }
}
fun country(block: Country.() -> Unit): Country {
    return Country().apply(block)
}