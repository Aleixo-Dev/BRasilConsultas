package br.com.nicolas.brasilconsulta.data.datasource.response


import com.google.gson.annotations.SerializedName

data class DirectResponse(
    @SerializedName("cities")
    val cities: List<String>,
    @SerializedName("state")
    val state: String
)