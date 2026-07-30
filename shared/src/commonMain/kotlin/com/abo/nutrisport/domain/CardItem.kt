package com.abo.nutrisport.domain

import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
data class CardItem(
    val id : String = Uuid.Companion.random().toHexString(),
    val productId : String,
    val flavor : String? = null,
    val quantity : Int,
)
