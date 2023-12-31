package com.po.ballguru.ui.theme.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.po.ballguru.ui.theme.common.MatchesDataState
import com.po.ballguru.ui.theme.common.MatchInfoVo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MatchInfoViewModel @Inject constructor(

) : ViewModel() {
    val response: MutableState<MatchesDataState> = mutableStateOf(MatchesDataState.Empty)
    init {
        fetchMatchesDataFromFireBase()
    }
    private fun fetchMatchesDataFromFireBase() {
        val tempList = mutableListOf<MatchInfoVo>()
        response.value = MatchesDataState.Loading
        FirebaseDatabase
            .getInstance()
            .getReference("today-match")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    response.value = MatchesDataState.Failure(error.message)
                }
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (DataSnap in snapshot.children) {
                        val matchItem = DataSnap.getValue(MatchInfoVo::class.java)
                        if (matchItem != null)
                            tempList.add(matchItem)
                    }
                    response.value = MatchesDataState.Success(tempList)
                }
            })
        }
}