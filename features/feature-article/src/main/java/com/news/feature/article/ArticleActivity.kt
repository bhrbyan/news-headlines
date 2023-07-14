package com.news.feature.article

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.res.stringResource
import com.news.ui.component.CommonMainScreen
import com.news.ui.theme.NewsHeadlinesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsHeadlinesTheme {
                CommonMainScreen(
                    title = stringResource(id = R.string.article_title),
                    hasNavigation = true,
                    onClickNavigation = { onBackPressedDispatcher.onBackPressed() }
                ) {
                    val source = intent.getStringExtra(ArticleConstants.KEY_EXTRA_SOURCE) ?: ""
                    ScreenArticle(
                        source = source,
                        onClickArticle = {
                            // Go to detail article
                        }
                    )
                }
            }
        }
    }

}