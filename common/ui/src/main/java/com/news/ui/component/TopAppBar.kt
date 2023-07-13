package com.news.ui.component

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun NewsHeadlineTopAppBar(title: String) {
    TopAppBar(
        title = {
            Text(text = title)
        }
    )
}