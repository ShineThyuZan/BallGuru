package com.po.ballguru.ui.theme.screen.news

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.po.ballguru.ui.theme.common.NewsVo
import com.po.ballguru.ui.theme.common.SheetHeader
import com.po.ballguru.ui.theme.common.VerticalSpacerBase
import com.po.ballguru.ui.theme.common.VerticalSpacerBase2x
import com.po.ballguru.ui.theme.resources.dimen
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
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
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
            Text(
                modifier = Modifier.padding(start = MaterialTheme.dimen.base),
                text = news.author!!,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Blue,
                maxLines = 1,
            )
            VerticalSpacerBase2x()
            AsyncImage(
                modifier = Modifier
                    .height(280.dp)
                    .fillMaxWidth(),
                model = news.photo,
                contentDescription = "news image",
                contentScale = ContentScale.Crop
            )
            VerticalSpacerBase2x()
            ClickableText(
                text = annotatedText,
                style = (MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Start)),
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
