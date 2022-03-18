package br.com.nicolas.brasilconsulta.common

object DateFormat {
    fun formatDateFromApi(resultDate: String): String {

        val dateSplit = resultDate.split("-", " ")
        val result = arrayListOf(dateSplit[2], dateSplit[1], dateSplit[0])
        return result[0] + "/" + result[1] + "/" + result[2]
    }
}