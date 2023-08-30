package com.po.ballguru.ui.theme.screen.home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.po.ballguru.R
import com.po.ballguru.ui.theme.common.VerticalSpacerBase
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
            annotation = "https://t.me/sportingbird/2",
            start = startIndex,
            end = endIndex,
        )
    }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val url = "https://t.me/sportingbird/2"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        intent
                            .resolveActivity(context.packageManager)
                            ?.let {
                                ContextCompat.startActivity(context, intent, null)
                            }
                    },
                painter = painterResource(id = R.drawable.promo),
                contentDescription = "ads",
                contentScale = ContentScale.Fit,
            )
            VerticalSpacerBase()
            Text(
                text = "Ads ကျော်နည်းကြည့်ရန် အပေါ်ကကြော်ငြာကို နှိပ်ပါ",
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
               fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodySmall
            )
        }



    /* Card(
         modifier = Modifier
             .fillMaxWidth()
             .height(MaterialTheme.dimen.base_11x)
             .padding(MaterialTheme.dimen.base)
     ) {
         Row(modifier = Modifier.fillMaxSize()) {

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
     }*/
}

@Composable
@Preview
private fun CardItemForAdsPreview() {
    Surface {
        CardItemForAds()
    }
}