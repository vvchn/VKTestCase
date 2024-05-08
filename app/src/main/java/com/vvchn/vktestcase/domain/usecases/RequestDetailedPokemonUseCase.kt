package com.vvchn.vktestcase.domain.usecases

import androidx.paging.PagingSource
import com.vvchn.avitotesttask.common.Resource
import com.vvchn.vktestcase.domain.models.PokemonDetailed
import com.vvchn.vktestcase.domain.repository.PokeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

class RequestDetailedPokemonUseCase(private val repository: PokeRepository) {
    operator fun invoke(
        url: String,
    ): Flow<Resource<PokemonDetailed>> = flow {
        try {
            emit(Resource.Loading())
            val countries = repository.requestDetailedPokemon(url)
            emit(Resource.Success(countries))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage?.let { ": ${e.code()}" }))
        } catch (e: SocketTimeoutException) {
            emit(Resource.Error(e.localizedMessage?.let { ": ${e.message}" }))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage?.let { ": ${e.message}" }))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage?.let { ": ${e.message}" }))
        }
    }
}