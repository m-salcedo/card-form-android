package com.mercadolibre.android.cardform.data.model.body

import com.google.gson.annotations.SerializedName

data class FinishInscriptionBody(
    val siteId: String,
    @SerializedName("token")
    val tbkToken: String,
    @SerializedName("cardholder")
    val cardHolder: CardHolder
)