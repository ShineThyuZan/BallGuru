package com.po.ballguru.ui.theme.screen.news

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.po.ballguru.ui.theme.common.NewsVo
import com.po.ballguru.ui.theme.common.VerticalSpacerBase2x
import com.po.ballguru.ui.theme.screen.home.BannerAds

@Composable
fun ShowNewsLazyList(news : MutableList<NewsVo>){
    LazyColumn{
        items(news) {news->
            CardNewsItem(news = news)
            BannerAds(modifier = Modifier
                .fillMaxWidth())
            VerticalSpacerBase2x()
        }
    }
}
