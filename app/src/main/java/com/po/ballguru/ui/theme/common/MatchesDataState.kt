package com.po.ballguru.ui.theme.common

sealed class MatchesDataState {
    class Success(val data: MutableList<MatchInfoVo>) : MatchesDataState()
    class Failure(val message: String) : MatchesDataState()
    object Loading : MatchesDataState()
    object Empty : MatchesDataState()
}

