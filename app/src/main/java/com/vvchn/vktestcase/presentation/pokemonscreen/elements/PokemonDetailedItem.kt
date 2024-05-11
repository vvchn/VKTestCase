package com.vvchn.vktestcase.presentation.pokemonscreen.elements

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.request.RequestOptions
import com.vvchn.vktestcase.R
import com.vvchn.vktestcase.domain.models.PokemonDetailed
import com.vvchn.vktestcase.domain.models.Stat
import com.vvchn.vktestcase.presentation.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun PokemonDetailedItem(
    pokemon: PokemonDetailed,
    pullToRefreshState: PullToRefreshState,
) {
    var isLocationsExpanded by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
            .nestedScroll(pullToRefreshState.nestedScrollConnection),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GlideImage(
            model = pokemon.image,
            contentDescription = "image of ${pokemon.name} pokemon",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 40.dp)
                .border(width = 1.dp, color = MaterialTheme.colorScheme.surfaceVariant),
            alignment = Alignment.TopCenter
        ) {
            it.error(R.drawable.not_found).placeholder(R.drawable.placeholder_image)
                .apply(RequestOptions().fitCenter())
        }

        Text(
            text = pokemon.name,
            style = Typography.displayMedium,
            modifier = Modifier.padding(bottom = 40.dp),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp),
            thickness = Dp.Hairline
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "${stringResource(id = R.string.abilities)} " + if (pokemon.abilities.isNotEmpty()) {
                    pokemon.abilities.toString().clearFromBrackets()
                } else {
                    stringResource(id = R.string.unknown)
                },
                style = Typography.titleMedium,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Text(
                text = "${stringResource(id = R.string.forms)} " + if (pokemon.forms.isNotEmpty()) {
                    pokemon.forms.toString().clearFromBrackets()
                } else {
                    stringResource(
                        id = R.string.unknown
                    )
                },
                style = Typography.titleMedium,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Text(
                text = "${stringResource(id = R.string.height)} " + pokemon.height.toString(),
                style = Typography.titleMedium,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Text(
                text = "${stringResource(id = R.string.id)} " + pokemon.id.toString(),
                style = Typography.titleMedium,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Text(
                text = "${stringResource(id = R.string.can_be_found)} " + if (pokemon.locations.isNotEmpty()) {
                    pokemon.locations.toString().clearFromBrackets()
                } else {
                    stringResource(R.string.unknown)
                },
                style = Typography.titleMedium,
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .clickable { isLocationsExpanded = !isLocationsExpanded },
                overflow = TextOverflow.Ellipsis,
                maxLines = if (isLocationsExpanded) Int.MAX_VALUE else 3

            )
            Text(
                text = "${stringResource(id = R.string.stats)}" + if (pokemon.stats.isEmpty()) {
                    stringResource(
                        id = R.string.unknown
                    )
                } else {
                    ""
                },
                style = Typography.titleMedium,
                modifier = Modifier.padding(),
                overflow = TextOverflow.Ellipsis,
            )
            pokemon.stats.forEach { stat ->
                Text(
                    text = "${stat.name}: ${stat.baseStat}",
                    style = Typography.titleMedium,
                    modifier = Modifier.padding(),
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Text(
                text = "${stringResource(id = R.string.weight)} " + "${pokemon.weight}",
                style = Typography.titleMedium,
                modifier = Modifier.padding(top = 10.dp),
                overflow = TextOverflow.Ellipsis,
            )

        }
    }
}

private fun String.clearFromBrackets(): String {
    return this.filter { it !in "[\\]" }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PokemonDetailedItemPreview(modifier: Modifier = Modifier) {
    val dummy = PokemonDetailed(
        abilities = listOf<String>("static", "lightning-rod"),
        forms = listOf<String>("pikachu"),
        image = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png",
        height = 4,
        id = 25,
        locations = listOf<String>(
            "trophy-garden-area",
            "pallet-town-area",
            "kanto-route-2-south-towards-viridian-city",
            "viridian-forest-area",
            "power-plant-area",
            "hoenn-safari-zone-sw",
            "hoenn-safari-zone-se",
            "kalos-route-3-area",
            "santalune-forest-area",
            "slateport-city-contest-hall",
            "verdanturf-town-contest-hall",
            "fallarbor-town-contest-hall",
            "lilycove-city-contest-hall",
            "alola-route-1-east",
            "alola-route-1-wes",
            "hauoli-city-shopping-district",
            "heahea-city-surf-association",
        ),
        name = "pikachu",
        stats = listOf<Stat>(
            Stat(35, "hp"),
            Stat(55, "attack"),
            Stat(40, "defense"),
            Stat(50, "special-attack"),
            Stat(50, "special-defence"),
            Stat(90, "speed"),
        ),
        weight = 60
    )
    PokemonDetailedItem(pokemon = dummy, pullToRefreshState = rememberPullToRefreshState())
}