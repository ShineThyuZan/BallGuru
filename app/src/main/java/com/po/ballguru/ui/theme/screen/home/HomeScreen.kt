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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.po.ballguru.R
import com.po.ballguru.ui.theme.common.MatchesDataState
import com.po.ballguru.ui.theme.graph.Destination
import com.po.ballguru.ui.theme.resources.dimen
import com.po.ballguru.ui.theme.viewmodel.MatchInfoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: MatchInfoViewModel = hiltViewModel()
) {
    val context = LocalContext.current
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
                    .padding(bottom = MaterialTheme.dimen.base_2x)
            ) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .weight(2f)) {
                    CardItemForAds()
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .weight(14f)) {
                    SetFireBaseData(viewModel)
                }
                BannerAds(modifier = Modifier
                    .padding(MaterialTheme.dimen.base)
                    .fillMaxWidth()
                    .weight(2f))
            }
        }
    }
}

@Composable
fun SetFireBaseData(viewModel: MatchInfoViewModel) {
    when (val result = viewModel.response.value) {
        MatchesDataState.Empty -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Error")
            }
        }

        is MatchesDataState.Failure -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = result.message)
            }
        }

        MatchesDataState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is MatchesDataState.Success -> {
            ShowLazyList(result.data)
        }
    }
}


