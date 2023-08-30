package com.po.ballguru.ui.theme.screen.home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.po.ballguru.R
import com.po.ballguru.ui.theme.common.HorizontalSpacerBase2x
import com.po.ballguru.ui.theme.common.HorizontalSpacerSmall
import com.po.ballguru.ui.theme.common.MatchInfoVo
import com.po.ballguru.ui.theme.resources.dimen

@Composable
fun CardItemContent(match: MatchInfoVo) {
    val context = LocalContext.current
    val matchLink = buildAnnotatedString {
        val watchNowStr = "Watch Now"
        val startIndex = 0
        val endIndex = startIndex + 9
        append(watchNowStr)
        addStyle(
            style = SpanStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline,
                fontStyle = FontStyle.Italic
            ),
            start = startIndex,
            end = endIndex
        )
        addStringAnnotation(
            tag = "URL",
            annotation = match.link.toString(),
            start = startIndex,
            end = endIndex,
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = match.match_name!!,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(top = MaterialTheme.dimen.base),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleMedium,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.width(120.dp),
                text = match.first_team!!,
                maxLines = 2,
                textAlign = TextAlign.Center
            )

            HorizontalSpacerBase2x()

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(match.first_team_logo)
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.Inside,
                modifier = Modifier.size(24.dp)
            )

            HorizontalSpacerSmall()

            Text(text = "Vs")

            HorizontalSpacerSmall()

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(match.second_team_logo)
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.Inside,
                modifier = Modifier.size(24.dp)
            )

            HorizontalSpacerBase2x()

            Text(
                modifier = Modifier.width(120.dp),
                maxLines = 2,
                text = match.second_team!!,
                textAlign = TextAlign.Center
            )
        }
        Text(
            text = match.match_time.toString(),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(MaterialTheme.dimen.base),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = match.quality.toString(),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(MaterialTheme.dimen.base),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium
        )
        ClickableText(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = MaterialTheme.dimen.base),
            text = matchLink,
            onClick = { offset ->
                matchLink.getStringAnnotations(
                    tag = "URL",
                    start = offset,
                    end = offset
                ).firstOrNull()?.let { annotation ->
                    val url = annotation.item
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    intent.resolveActivity(context.packageManager)?.let {
                        ContextCompat.startActivity(context, intent, null)
                    }
                }
            }
        )
        Image(
            painter = painterResource(id = R.drawable.football),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(MaterialTheme.dimen.base_5x)
                .padding(MaterialTheme.dimen.base)
                .clip(shape = CircleShape),
            contentDescription = "logo",
            contentScale = ContentScale.Inside
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(MaterialTheme.dimen.base),
        ) {
            Text(
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                text = "Prediction",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodySmall,
            )
            Text(
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                text = match.prediction.toString(),
                color = Color.Red,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.End
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun CardItemContentPreview() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        CardItemContent(
            match = MatchInfoVo(
                "Premier League",
                first_team = "Red Team",
                second_team = "Blue Team",
                link = "http://myanmar.ict.com",
                quality = "480hd",
                match_time = "8:30pm",
                prediction = "3:3"
            )
        )
    }
}