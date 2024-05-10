package com.vvchn.vktestcase.presentation.mainscreen.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.request.RequestOptions
import com.vvchn.vktestcase.R
import com.vvchn.vktestcase.domain.models.Pokemon

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PokemonItem(
    pokemon: Pokemon,
) {
    val roundedCornerShape = RoundedCornerShape(topStart = 20.dp, bottomStart = 20.dp)
    Row(
        modifier = Modifier
            .fillMaxHeight()
//            .background(ApplicationTheme.colors.backgrounds.secondary, roundedCornerShape)
            .clip(roundedCornerShape)
    ) {
        GlideImage(
            model = pokemon.url,
            contentDescription = "image of ${pokemon.name} pokemon (svg)",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(128.dp)
                .clip(shape = MaterialTheme.shapes.small)
        ) {
            it.error(R.drawable.not_found).placeholder(R.drawable.placeholder_image)
                .apply(RequestOptions().fitCenter())
        }

        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f)
        ) {
            Text(
                text = pokemon.name,
//                style = ApplicationTheme.typography.headlines.h1
            )
        }
    }
}