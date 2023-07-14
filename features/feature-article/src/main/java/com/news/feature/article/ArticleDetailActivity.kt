package com.news.feature.article

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.res.stringResource
import com.news.ui.component.CommonMainScreen
import com.news.ui.component.CommonWebView
import com.news.ui.theme.NewsHeadlinesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsHeadlinesTheme {
                CommonMainScreen(
                    title = stringResource(id = R.string.article_title),
                    hasNavigation = true,
                    hasAction = false,
                    onClickNavigation = { onBackPressedDispatcher.onBackPressed() },
                    onClickAction = {}
                ) {
                    val url = intent.getStringExtra(ArticleConstants.KEY_EXTRA_URL) ?: ""
                    CommonWebView(url = url)
                }
            }
        }
    }

}