package com.po.ballguru.ui.theme.common

sealed class DataState {
    class Success(val data: MutableList<MatchInfoVo>) : DataState()
    class Failure(val message: String) : DataState()
    object Loading : DataState()
    object Empty : DataState()
}

sealed class NewsDataState {
    class Success(val data: MutableList<NewsVo>) : NewsDataState()
    class Failure(val message: String) : NewsDataState()
    object Loading : NewsDataState()
    object Empty : NewsDataState()

}