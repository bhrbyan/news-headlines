package com.news.feature.article

import android.content.Intent
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
                    title = stringResource(id = R.string.articles_title),
                    hasNavigation = true,
                    hasAction = true,
                    onClickNavigation = { onBackPressedDispatcher.onBackPressed() },
                    onClickAction = {}
                ) {
                    val source = intent.getStringExtra(ArticleConstants.KEY_EXTRA_SOURCE) ?: ""
                    ScreenArticle(
                        source = source,
                        onClickArticle = { url ->
                            startActivity(
                                Intent(this, ArticleDetailActivity::class.java).apply {
                                    putExtra(ArticleConstants.KEY_EXTRA_URL, url)
                                }
                            )
                        }
                    )
                }
            }
        }
    }

}