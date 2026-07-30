package com.abo.nutrisport.domain

import kotlinx.serialization.Serializable

@Serializable
data class Customer(
    val id : String,
    val firstname : String,
    val lastname : String,
    val email : String,
    val city : String? = null,
    val postalCode : Int? = null,
    val address : String? = null,
    val phoneNumber : PhoneNumber? = null,
    val cart : List<CardItem> = emptyList(),
)

@Serializable
data class PhoneNumber(
    val number : String,
    val dialCode : Int,
)
