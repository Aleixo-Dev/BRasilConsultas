package br.com.nicolas.brasilconsulta.data.datasource.response


import com.google.gson.annotations.SerializedName

data class CepResponse(
    @SerializedName("cep")
    val cep: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("neighborhood")
    val neighborhood: String,
    @SerializedName("service")
    val service: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("street")
    val street: String
)