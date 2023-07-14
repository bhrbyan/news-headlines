package com.news.headlines

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.res.stringResource
import com.news.feature.category.CategoryUtil
import com.news.feature.category.ScreenCategory
import com.news.feature.source.SourceActivity
import com.news.feature.source.SourceConstants
import com.news.ui.R
import com.news.ui.component.CommonMainScreen
import com.news.ui.theme.NewsHeadlinesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsHeadlinesTheme {
                CommonMainScreen(title = stringResource(id = R.string.app_name),
                    hasNavigation = false,
                    hasAction = false,
                    onClickNavigation = {},
                    onClickAction = {}
                ) {
                    val categories = CategoryUtil.getCategories(this)
                    ScreenCategory(
                        categories = categories,
                        onClickCategory = { id, name ->
                            startActivity(
                                Intent(this, SourceActivity::class.java).apply {
                                    putExtra(SourceConstants.KEY_EXTRA_CATEGORY_ID, id)
                                    putExtra(SourceConstants.KEY_EXTRA_CATEGORY_NAME, name)
                                }
                            )
                        }
                    )
                }
            }
        }
    }
}