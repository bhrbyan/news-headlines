package com.news.headlines

import android.content.Intent
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
import com.news.feature.category.CategoryUtil
import com.news.feature.category.ScreenCategory
import com.news.feature.source.SourceActivity
import com.news.feature.source.SourceConstants
import com.news.ui.R
import com.news.ui.component.CommonTopAppBar
import com.news.ui.theme.NewsHeadlinesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsHeadlinesTheme {
                Scaffold(
                    topBar = {
                        CommonTopAppBar(
                            title = stringResource(id = R.string.app_name),
                            hasNavigation = false,
                            onClickNavigation = {})
                    },
                    content = {
                        Surface(
                            modifier = Modifier
                                .padding(it)
                                .fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
                            val categories = CategoryUtil.getCategories(this)
                            ScreenCategory(
                                categories = categories,
                                onClickCategory = { category ->
                                    startActivity(
                                        Intent(this, SourceActivity::class.java).apply {
                                            putExtra(SourceConstants.KEY_EXTRA_CATEGORY, category)
                                        }
                                    )
                                }
                            )
                        }
                    }
                )
            }
        }
    }
}