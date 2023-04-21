package com.khanAppsNJ.countries.model
/**
 * The `Country` class represents a country with its name, capital, and population.
 *
 * @property name The name of the country.
 * @property capital The capital of the country.
 * @property code The code of the country.
 * @property region The region of the country.
 */

data class Country(
    val name: String,
    val region: String,
    val code: String,
    val capital: String,){

    override fun toString(): String {
        return " Country is $name " +
                "\n Capital is $capital " +
                "\n Region is $region" +
                "\n Code is $code"
    }
}
