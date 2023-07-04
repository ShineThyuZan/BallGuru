package com.po.ballguru.ui.theme.screen.news

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.po.ballguru.ui.theme.common.NewsVo
import com.po.ballguru.ui.theme.common.VerticalSpacerBase2x
@Composable
fun ShowNewsLazyList(news : MutableList<NewsVo>){
    LazyColumn{
        items(news) {news->
            CardNewsItem(news = news)
            VerticalSpacerBase2x()
        }
    }
}
