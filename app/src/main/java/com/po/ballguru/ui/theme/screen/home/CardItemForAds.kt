package com.po.ballguru.ui.theme.screen.home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.po.ballguru.R

@Composable
fun CardItemForAds() {
    val context = LocalContext.current
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    val url = "https://myanmarsports.net"
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
fun CardItemFor1X() {
    val context = LocalContext.current
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    val url = "https://myanmarsports.net"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    intent
                        .resolveActivity(context.packageManager)
                        ?.let {
                            ContextCompat.startActivity(context, intent, null)
                        }
                },
            painter = painterResource(id = R.drawable.promo_ads),
            contentDescription = "ads",
            contentScale = ContentScale.Fit,
        )
    }
}




@Composable
@Preview
private fun CardItemForAdsPreview() {
    Surface {
        CardItemForAds()
    }
}