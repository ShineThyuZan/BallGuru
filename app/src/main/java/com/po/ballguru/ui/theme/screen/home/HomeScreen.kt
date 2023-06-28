package com.po.ballguru.ui.theme.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.po.ballguru.R
import com.po.ballguru.ui.theme.common.DataState
import com.po.ballguru.ui.theme.graph.Destination
import com.po.ballguru.ui.theme.resources.dimen
import com.po.ballguru.ui.theme.viewmodel.MatchInfoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: MatchInfoViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            SmallTopAppBar(
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = stringResource(id = R.string.app_name))
                    }
                },
                actions = {
                    Text(
                        modifier = Modifier
                            .padding(end = MaterialTheme.dimen.base)
                            .clickable {
                                navController.navigate(Destination.News.route)
                            },
                        text = "News",
                    )
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier.size(MaterialTheme.dimen.base_8x),
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "logo"
                    )
                }
            )
        },
        snackbarHost = {},
        containerColor = Color.Transparent
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = MaterialTheme.dimen.base_3x)
            ) {
                CardItemForAds()
                SetFireBaseData(viewModel)
            }
        }
    }
}

@Composable
fun SetFireBaseData(viewModel: MatchInfoViewModel) {
    when (val result = viewModel.response.value) {
        DataState.Empty -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Error")
            }
        }

        is DataState.Failure -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = result.message)
            }
        }

        DataState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is DataState.Success -> {
            ShowLazyList(result.data)
        }
    }
}


