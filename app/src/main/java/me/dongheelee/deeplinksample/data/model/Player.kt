package me.dongheelee.deeplinksample.data.model

data class Player(
    val id: Long,
    val number: Int,
    val city: String,
    val name: String,
    val country: String,
    val thumbnail: String
) {

    fun formattedNumber() = "No. $number"

    fun cityCountry() = "$city, $country"
}
