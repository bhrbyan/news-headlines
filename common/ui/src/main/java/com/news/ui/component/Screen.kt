package com.news.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CommonMainScreen(
    title: String,
    hasNavigation: Boolean,
    modifier: Modifier = Modifier,
    onClickNavigation: () -> Unit,
    screenContent: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            CommonTopAppBar(
                title = title,
                hasNavigation = hasNavigation,
                onClickNavigation = { onClickNavigation() }
            )
        },
        content = {
            Surface(
                modifier = modifier
                    .padding(it)
                    .fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                screenContent()
            }
        }
    )
}