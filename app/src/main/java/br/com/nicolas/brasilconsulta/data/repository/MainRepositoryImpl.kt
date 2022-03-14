package br.com.nicolas.brasilconsulta.data.repository

import br.com.nicolas.brasilconsulta.common.Resource
import br.com.nicolas.brasilconsulta.data.datasource.remote.RemoteDataSource
import br.com.nicolas.brasilconsulta.data.datasource.response.CepResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class MainRepositoryImpl(
    private val remote: RemoteDataSource
) : MainRepository {

    override suspend fun fetchCep(cep: String): Flow<Resource<CepResponse>> = flow {
        try {
            emit(Resource.Loading)
            val response = remote.getCep(cep)
            emit(Resource.Success(response))
        } catch (exception: HttpException) {
            emit(Resource.Error(exception.message() ?: "no internet connection."))
        } catch (exception: IOException) {
            emit(Resource.Error(exception.toString()))
        }
    }
}