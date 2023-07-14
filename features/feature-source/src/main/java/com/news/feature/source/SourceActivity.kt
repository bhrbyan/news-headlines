package com.news.feature.source

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.news.ui.component.CommonTopAppBar
import com.news.ui.theme.NewsHeadlinesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SourceActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsHeadlinesTheme {
                Scaffold(
                    topBar = {
                        CommonTopAppBar(
                            title = stringResource(id = R.string.source_title),
                            hasNavigation = true,
                            onClickNavigation = { onBackPressedDispatcher.onBackPressed() }
                        )
                    },
                    content = {
                        Surface(
                            modifier = Modifier
                                .padding(it)
                                .fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
                            val category: String =
                                intent.getStringExtra(SourceConstants.KEY_EXTRA_CATEGORY) ?: ""

                            ScreenSource(
                                category = category,
                                onClickSource = {

                                }
                            )
                        }
                    }
                )
            }
        }
    }

}