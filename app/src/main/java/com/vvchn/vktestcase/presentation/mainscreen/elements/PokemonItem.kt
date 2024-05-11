package com.vvchn.vktestcase.presentation.mainscreen.elements

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.request.RequestOptions
import com.vvchn.vktestcase.R
import com.vvchn.vktestcase.domain.models.Pokemon
import com.vvchn.vktestcase.presentation.navigation.Route

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PokemonItem(
    pokemon: Pokemon,
    navController: NavController,
) {
    val roundedCornerShape = RoundedCornerShape(20.dp)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(horizontal = 20.dp)
            .clip(roundedCornerShape)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable {
                val pokemonEncodedUrl = Uri.encode(pokemon.url)
                navController.navigate("${Route.PokemonDetailedScreen.route}/${pokemonEncodedUrl}")
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            GlideImage(
                model = pokemon.image,
                contentDescription = "image of ${pokemon.name} pokemon",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = MaterialTheme.shapes.small)
            ) {
                it.error(R.drawable.not_found).placeholder(R.drawable.placeholder_image)
                    .apply(RequestOptions().fitCenter())
            }

            Text(
                text = pokemon.name,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

        }
    }
}