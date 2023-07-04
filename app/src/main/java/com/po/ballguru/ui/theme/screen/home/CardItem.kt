package com.po.ballguru.ui.theme.screen.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.po.ballguru.ui.theme.common.MatchInfoVo
import com.po.ballguru.ui.theme.resources.dimen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardItem(match: MatchInfoVo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(MaterialTheme.dimen.base)
    ) {
        CardItemContent(match = match)
    }
}

