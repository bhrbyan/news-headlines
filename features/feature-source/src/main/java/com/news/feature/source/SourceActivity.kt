package com.news.feature.source

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.res.stringResource
import com.news.feature.article.ArticleActivity
import com.news.feature.article.ArticleConstants.KEY_EXTRA_SOURCE
import com.news.ui.component.CommonMainScreen
import com.news.ui.theme.NewsHeadlinesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SourceActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsHeadlinesTheme {
                CommonMainScreen(
                    title = stringResource(id = R.string.source_title),
                    hasNavigation = true,
                    hasAction = true,
                    onClickNavigation = { onBackPressedDispatcher.onBackPressed() },
                    onClickAction = {

                    }
                ) {
                    val category: String =
                        intent.getStringExtra(SourceConstants.KEY_EXTRA_CATEGORY) ?: ""

                    ScreenSource(
                        category = category,
                        onClickSource = { source ->
                            startActivity(
                                Intent(this, ArticleActivity::class.java).apply {
                                    putExtra(KEY_EXTRA_SOURCE, source)
                                }
                            )
                        }
                    )
                }
            }
        }
    }

}