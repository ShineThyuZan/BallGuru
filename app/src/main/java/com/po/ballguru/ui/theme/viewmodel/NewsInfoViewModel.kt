package com.po.ballguru.ui.theme.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.po.ballguru.ui.theme.common.NewsDataState
import com.po.ballguru.ui.theme.common.NewsVo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsInfoViewModel @Inject constructor() : ViewModel() {
    val response: MutableState<NewsDataState> = mutableStateOf(NewsDataState.Empty)
    init {
        fetchNewsDataFormFireBase()
    }
    private fun fetchNewsDataFormFireBase() {
        val tempList = mutableListOf<NewsVo>()
        response.value = NewsDataState.Loading
        FirebaseDatabase
            .getInstance()
            .getReference("news")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    response.value = NewsDataState.Failure(error.message)
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    for (DataSnap in snapshot.children) {
                        val matchItem = DataSnap.getValue(NewsVo::class.java)
                        if (matchItem != null)
                            tempList.add(matchItem)
                    }
                    response.value = NewsDataState.Success(tempList)
                }
            })
    }
}