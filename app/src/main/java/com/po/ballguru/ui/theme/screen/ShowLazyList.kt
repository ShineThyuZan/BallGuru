package com.po.ballguru.ui.theme.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.po.ballguru.ui.theme.common.MatchInfoVo

@Composable
fun ShowLazyList(matches: MutableList<MatchInfoVo>) {
    LazyColumn {
        items(matches) { match ->
            CardItem(match = match)
        }
    }
}
