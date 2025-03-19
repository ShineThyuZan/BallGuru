package com.po.ballguru.ui.theme.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.size.Dimension
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
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    Column(modifier = Modifier.padding(start = MaterialTheme.dimen.base).clickable{
                        navController.navigate(Destination.About.route)
                    },) {
                        Text(text = "About Us", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                    }
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

                Box(
                    Modifier
                        .fillMaxWidth()
                        .weight(2f)) {
                    CardItemFor1X()
                }
//                BannerAds(modifier = Modifier
//                  /*  .padding(MaterialTheme.dimen.base)*/
//                    .fillMaxSize()
//                    .weight(2f))

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


