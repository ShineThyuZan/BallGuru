package com.po.ballguru.ui.theme.screen.news

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import com.po.ballguru.ui.theme.common.NewsVo
import com.po.ballguru.ui.theme.common.VerticalSpacerBase2x
import com.po.ballguru.ui.theme.screen.home.CardItemForAds

@Composable
fun ShowNewsLazyList(news: MutableList<NewsVo>) {
    LazyColumn {
        itemsIndexed(news) { index, newsItem ->
            CardNewsItem(news = newsItem)
            if ((index + 1) % 2 == 0) {
                CardItemForAds()
            }
            VerticalSpacerBase2x()
        }
    }

}
