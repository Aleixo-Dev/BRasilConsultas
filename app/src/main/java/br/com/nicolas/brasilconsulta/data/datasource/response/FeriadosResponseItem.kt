package br.com.nicolas.brasilconsulta.data.datasource.response


import com.google.gson.annotations.SerializedName

data class FeriadosResponseItem(
    @SerializedName("date")
    var date: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String
)