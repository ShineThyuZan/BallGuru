package com.po.ballguru.ui.theme.screen.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.po.ballguru.ui.theme.common.MatchInfoVo
import com.po.ballguru.ui.theme.common.NewsVo
import com.po.ballguru.ui.theme.common.VerticalSpacerBase2x

@Composable
fun ShowLazyList(matches: MutableList<MatchInfoVo>) {
    LazyColumn {
        items(matches) { match ->
            CardItem(match = match)
        }
    }
}

@Composable
fun ShowNewsLazyList(news : MutableList<NewsVo>){
    LazyColumn{
        items(news) {news->
            CardNewsItem(news = news)
            VerticalSpacerBase2x()
        }
    }
}
