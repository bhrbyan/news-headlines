package com.news.feature.source

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.news.feature.article.ArticleActivity
import com.news.feature.article.ArticleConstants
import com.news.ui.component.CommonMainScreen
import com.news.ui.theme.NewsHeadlinesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SourceActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsHeadlinesTheme {
                val categoryId: String =
                    intent.getStringExtra(SourceConstants.KEY_EXTRA_CATEGORY_ID) ?: ""
                val categoryName: String =
                    intent.getStringExtra(SourceConstants.KEY_EXTRA_CATEGORY_NAME) ?: ""

                CommonMainScreen(
                    title = categoryName,
                    hasNavigation = true,
                    hasAction = true,
                    onClickNavigation = { onBackPressedDispatcher.onBackPressed() },
                    onClickAction = {

                    }
                ) {
                    ScreenSource(
                        category = categoryId,
                        onClickSource = { id, name ->
                            startActivity(
                                Intent(this, ArticleActivity::class.java).apply {
                                    putExtra(ArticleConstants.KEY_EXTRA_SOURCE_ID, id)
                                    putExtra(ArticleConstants.KEY_EXTRA_SOURCE_NAME, name)
                                }
                            )
                        }
                    )
                }
            }
        }
    }

}