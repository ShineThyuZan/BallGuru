package com.po.ballguru.ui.theme.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.po.ballguru.ui.theme.common.MatchInfoVo
import com.po.ballguru.ui.theme.common.NewsVo
import com.po.ballguru.ui.theme.common.SheetHeader
import com.po.ballguru.ui.theme.common.VerticalSpacerBase
import com.po.ballguru.ui.theme.common.VerticalSpacerBase2x
import com.po.ballguru.ui.theme.resources.dimen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardItem(match: MatchInfoVo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(MaterialTheme.dimen.base)
    ) {
        CardItemContent(match = match)
    }
}

/*@Composable
fun CardNewsItem(news: NewsVo) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var showMore by remember { mutableStateOf(false) }
        val text = news.content
        Column(modifier = Modifier.padding(MaterialTheme.dimen.base)) {
            Text(
                text = news.title!!,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = news.date!!,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
            )
            VerticalSpacerBase()
            AsyncImage(
                modifier = Modifier
                    .height(MaterialTheme.dimen.extra_large)
                    .fillMaxWidth(),
                model = news.photo,
                contentDescription = "news image",
                contentScale = ContentScale.Crop
            )
            VerticalSpacerBase()
            Column(modifier = Modifier
                .animateContentSize(animationSpec = tween(100))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { showMore = !showMore }) {
                if (showMore) {
                    Text(text = text!!)
                } else {
                    Text(text = text!!, maxLines = 3, overflow = TextOverflow.Ellipsis)
                }
            }
            VerticalSpacerBase2x()
            SheetHeader()
        }
    }
}*/


@Composable
fun CardNewsItem(news: NewsVo) {
    var isExpanded by remember { mutableStateOf(value = false) }
    var textLayoutResultState by remember { mutableStateOf<TextLayoutResult?>(value = null) }
    var adjustedText by remember { mutableStateOf(value = news.content) }
    val overflow = textLayoutResultState?.hasVisualOverflow ?: false
    val showOverflow = remember { mutableStateOf(value = false) }
    val showMore = "...Show More"
    val showLess = "...Show Less"
    LaunchedEffect(textLayoutResultState) {
        if (textLayoutResultState == null) return@LaunchedEffect
        if (!isExpanded && overflow) {
            showOverflow.value = true
            val lastCharIndex = textLayoutResultState!!.getLineEnd(lineIndex = 3 - 1)
            adjustedText = news.content!!
                .substring(startIndex = 0, endIndex = lastCharIndex)
                .dropLast(showMore.length)
                .dropLastWhile { it == ' ' || it == '.' }
        }
    }
    val annotatedText = buildAnnotatedString {
        if (isExpanded) {
            append(news.content!!)
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.surface,
                    fontSize = 14.sp
                )
            ) {
                pushStringAnnotation(tag = "showLess", annotation = "showLess")
                append(showLess)
                addStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 14.sp
                    ),
                    start = news.content.length,
                    end = news.content.length + showMore.length
                )
                pop()
            }
        } else {
            append(adjustedText!!)
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 14.sp
                )
            ) {
                if (showOverflow.value) {
                    pushStringAnnotation(tag = "showMore", annotation = "showMore")
                    append(showMore)
                    addStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 14.sp
                        ),
                        start = adjustedText!!.length,
                        end = adjustedText!!.length + showMore.length
                    )
                    pop()
                }
            }
        }
    }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Column(modifier = Modifier.padding(MaterialTheme.dimen.base)) {
            Text(
                text = news.title!!,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 3,
            )
            VerticalSpacerBase()
            Text(
                modifier = Modifier.padding(start = MaterialTheme.dimen.base),
                text = news.date!!,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
            )
            VerticalSpacerBase2x()
            AsyncImage(
                modifier = Modifier
                    .height(160.dp)
                    .fillMaxWidth(),
                model = news.photo,
                contentDescription = "news image",
                contentScale = ContentScale.Crop
            )
            VerticalSpacerBase2x()
            ClickableText(
                text = annotatedText,
                style = (MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Justify)),
                maxLines = if (isExpanded) Int.MAX_VALUE else 3,
                onTextLayout = { textLayoutResultState = it },
                onClick = { offset ->
                    annotatedText.getStringAnnotations(
                        tag = "showLess",
                        start = offset,
                        end = offset + showLess.length
                    ).firstOrNull()?.let {
                        isExpanded = !isExpanded
                    }
                    annotatedText.getStringAnnotations(
                        tag = "showMore",
                        start = offset,
                        end = offset + showMore.length
                    ).firstOrNull()?.let {
                        isExpanded = !isExpanded
                    }
                },
            )
            VerticalSpacerBase2x()
            SheetHeader()
        }
    }
}
