package com.vvchn.vktestcase.presentation.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vvchn.vktestcase.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoItemsPlaceholder(
    pullToRefreshState: PullToRefreshState = rememberPullToRefreshState(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 5.dp)
            .verticalScroll(rememberScrollState())
            .nestedScroll(pullToRefreshState.nestedScrollConnection),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            text = stringResource(id = R.string.nothing_here),
            maxLines = 2,
            textAlign = TextAlign.Center,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            text = stringResource(id = R.string.swipe_to_reload),
            maxLines = 2,
            textAlign = TextAlign.Center,
        )
    }
}