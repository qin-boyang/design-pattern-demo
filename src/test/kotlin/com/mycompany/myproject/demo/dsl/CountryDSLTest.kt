package com.mycompany.myproject.demo.dsl

import org.junit.jupiter.api.Test

class CountryDSLTest {

    @Test
    fun testCountryDSL() {
        val country = country {
            name = "USA"
            population = 300000000
            capital {
                name = "Washington"
                latitude = 38.8951
                longitude = -77.0364
            }
            state {
                name = "New York"
                city {
                    name = "New York"
                    population = 8000000
                }
                city {
                    name = "Buffalo"
                    population = 600000
                }
            }
            state {
                name = "California"
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

        println("${country.name}, ${country.population}, ")
        println("${country.capital?.name}, ${country.capital?.latitude}, ${country.capital?.longitude}}")
        for (state in country.states) {
            println("${state.name}")
            for (city in state.cities) {
                println("${city.name}, ${city.population}")
            }
        }
    }
}