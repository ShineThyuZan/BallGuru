package com.po.ballguru.ui.theme.common

sealed class NewsDataState {
    class Success(val data: MutableList<NewsVo>) : NewsDataState()
    class Failure(val message: String) : NewsDataState()
    object Loading : NewsDataState()
    object Empty : NewsDataState()
}