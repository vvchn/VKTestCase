package com.vvchn.vktestcase.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vvchn.vktestcase.data.remote.api.PokeApi
import com.vvchn.vktestcase.data.remote.api.PokemonsApi
import com.vvchn.vktestcase.data.remote.api.dtos.ApiResponse
import com.vvchn.vktestcase.data.remote.api.dtos.PokemonDto
import com.vvchn.vktestcase.data.remote.utils.toPokemon
import com.vvchn.vktestcase.domain.models.Pokemon
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

class PokemonsPagingSource (
    private val pokeApi: PokeApi,
    private val pokemonsApi: PokemonsApi,
) : PagingSource<Int, Pokemon>() {

    private var _nextPageUrl: String? = null
    private var loaded: Int = 0

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val nextPageUrl = _nextPageUrl
        val page = params.key ?: 1
        val loadSize = params.loadSize

        return try {
            val apiResponse: ApiResponse
            val pokemons: MutableList<PokemonDto> = mutableListOf()
            val nextKey: Int?
            val prevKey: Int?

            if (nextPageUrl == null) {
                apiResponse = pokeApi.requestResourcesList(loadSize)
            } else {
                apiResponse = pokeApi.continueRequestResoursesList(nextPageUrl)
            }

            _nextPageUrl = apiResponse.next

            apiResponse.results.forEach { pokemon ->
                pokemons.add(pokemonsApi.requestPokemon(pokemon.url))
            }

            nextKey = if (apiResponse.count == 0 || loaded >= apiResponse.count) null else page + 1
            prevKey = if (page == 1) null else page - 1

            return LoadResult.Page(
                data = pokemons.map { pokemonDto: PokemonDto -> pokemonDto.toPokemon() },
                nextKey = nextKey,
                prevKey = prevKey
            )
        } catch (e: HttpException) {
            LoadResult.Error(
                throwable = e
            )
        } catch (e: SocketTimeoutException) {
            LoadResult.Error(
                throwable = e
            )
        } catch (e: IOException) {
            LoadResult.Error(
                throwable = e
            )
        } catch (e: Exception) {
            LoadResult.Error(
                throwable = e
            )
        }

    }
}