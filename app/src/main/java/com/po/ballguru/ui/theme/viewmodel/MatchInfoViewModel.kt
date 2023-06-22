package com.po.ballguru.ui.theme.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.po.ballguru.ui.theme.common.DataState
import com.po.ballguru.ui.theme.common.MatchInfoVo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MatchInfoViewModel @Inject constructor(

) : ViewModel() {
    val response: MutableState<DataState> = mutableStateOf(DataState.Empty)

    init {
        fetchMatchesDataFromFireBase()
    }

    private fun fetchMatchesDataFromFireBase() {
        val tempList = mutableListOf<MatchInfoVo>()
        response.value = DataState.Loading
        FirebaseDatabase
            .getInstance()
            .getReference("today-match")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    response.value = DataState.Failure(error.message)
                }
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (DataSnap in snapshot.children) {
                        val matchItem = DataSnap.getValue(MatchInfoVo::class.java)
                        if (matchItem != null)
                            tempList.add(matchItem)
                    }
                    response.value = DataState.Success(tempList)
                }
            })
        }
}