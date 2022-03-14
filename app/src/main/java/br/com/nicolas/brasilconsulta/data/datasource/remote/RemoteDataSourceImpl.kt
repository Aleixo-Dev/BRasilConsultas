package br.com.nicolas.brasilconsulta.data.datasource.remote

class RemoteDataSourceImpl(
    private val apiService: ApiService
) : RemoteDataSource {

    override suspend fun getCep(cep: String) = apiService.getCep(cep)
}