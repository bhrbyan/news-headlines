package com.news.ui.component

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CommonTopAppBar(
    title: String,
    hasNavigation: Boolean,
    onClickNavigation: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (hasNavigation) {
        TopAppBar(
            title = {
                Text(text = title)
            },
            navigationIcon = {
                IconButton(
                    onClick = {
                        onClickNavigation()
                    },
                    content = {
                        Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
                    }
                )
            },
            modifier = modifier
        )
    } else {
        TopAppBar(
            title = {
                Text(text = title)
            },
            modifier = modifier
        )
    }
}