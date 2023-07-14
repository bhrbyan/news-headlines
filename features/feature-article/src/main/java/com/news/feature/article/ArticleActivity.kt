package com.news.feature.article

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.news.ui.component.CommonMainScreen
import com.news.ui.theme.NewsHeadlinesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsHeadlinesTheme {
                val sourceId = intent.getStringExtra(ArticleConstants.KEY_EXTRA_SOURCE_ID) ?: ""
                val sourceName = intent.getStringExtra(ArticleConstants.KEY_EXTRA_SOURCE_NAME) ?: ""

                CommonMainScreen(
                    title = sourceName,
                    hasNavigation = true,
                    hasAction = true,
                    onClickNavigation = { onBackPressedDispatcher.onBackPressed() },
                    onClickAction = {}
                ) {
                    ScreenArticle(
                        source = sourceId,
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