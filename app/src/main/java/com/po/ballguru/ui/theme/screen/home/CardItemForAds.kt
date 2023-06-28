package com.po.ballguru.ui.theme.screen.home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.core.content.ContextCompat
import com.po.ballguru.R
import com.po.ballguru.ui.theme.resources.dimen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardItemForAds() {
    val context = LocalContext.current
    val adsLink = buildAnnotatedString {
        // str.indexOf(match.link.toString())
        val str = "Google ads ကျော်နည်း ကြည့်ရန်"
        val startIndex = 20
        val endIndex = startIndex + 9
        append(str)
        addStyle(
            style = SpanStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            ),
            start = startIndex,
            end = endIndex
        )
        addStringAnnotation(
            tag = "URL",
            annotation = "https://shop.com.mm",
            start = startIndex,
            end = endIndex,
        )
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.dimen.base_10x)
            .padding(MaterialTheme.dimen.base)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier
                    .width(MaterialTheme.dimen.base_10x)
                    .fillMaxHeight(),
                painter = painterResource(id = R.drawable.ads_bg),
                contentDescription = "ads",
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                ClickableText(
                    text = adsLink,
                    onClick = { offset ->
                        adsLink.getStringAnnotations(
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
                    },
                )
            }
        }
    }
}