package com.vvchn.vktestcase.domain.usecases

import com.vvchn.avitotesttask.common.Resource
import com.vvchn.vktestcase.domain.models.PokemonDetailed
import com.vvchn.vktestcase.domain.repository.PokeRepository
import com.vvchn.vktestcase.shared.NetworkRequestExceptions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class RequestDetailedPokemonUseCase @Inject constructor(private val repository: PokeRepository) {
    operator fun invoke(
        url: String,
    ): Flow<Resource<PokemonDetailed>> = flow {
        try {
            emit(Resource.Loading())
            val pokemonDetailed = repository.requestDetailedPokemon(url)
            emit(Resource.Success(pokemonDetailed))
        } catch (e: HttpException) {
            emit(Resource.Error(throwable = NetworkRequestExceptions.NetworkError(e.code())))
        } catch (e: SocketTimeoutException) {
            emit(Resource.Error(throwable = NetworkRequestExceptions.TimeOutError()))
        } catch (e: IOException) {
            emit(Resource.Error(throwable = NetworkRequestExceptions.ConnectionError()))
        } catch (e: RuntimeException) {
            emit(Resource.Error(throwable = NetworkRequestExceptions.ConnectionError()))
        } catch (e: Exception) {
            emit(Resource.Error(throwable = NetworkRequestExceptions.UnknownError()))
        }
    }
}