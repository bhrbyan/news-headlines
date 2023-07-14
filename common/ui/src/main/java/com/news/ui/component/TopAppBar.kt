package com.news.ui.component

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CommonTopAppBar(
    title: String,
    hasNavigation: Boolean,
    hasAction: Boolean,
    onClickNavigation: () -> Unit,
    onClickAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when {
        hasNavigation && hasAction -> {
            TopAppBarWithNavigationAndAction(
                title = title,
                onClickNavigation = { onClickNavigation() },
                onClickAction = { onClickAction() }
            )
        }

        hasNavigation -> {
            TopAppBarWithNavigation(
                title = title,
                onClickNavigation = { onClickNavigation() },
                modifier = modifier
            )
        }

        hasAction -> {
            TopAppBarWithAction(
                title = title,
                onClickAction = { onClickAction() }
            )
        }

        else -> {
            TopAppBarDefault(title = title, modifier = modifier)
        }
    }
}

@Composable
private fun TopAppBarDefault(
    title: String,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = title) },
        modifier = modifier
    )
}

@Composable
private fun TopAppBarWithNavigation(
    title: String,
    onClickNavigation: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(
                onClick = { onClickNavigation() },
                content = {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = null
                    )
                }
            )
        },
        modifier = modifier
    )
}

@Composable
private fun TopAppBarWithAction(
    title: String,
    onClickAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = title) },
        actions = {
            IconButton(
                onClick = { onClickAction() },
                content = {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = null
                    )
                }
            )
        },
        modifier = modifier
    )
}

@Composable
private fun TopAppBarWithNavigationAndAction(
    title: String,
    onClickNavigation: () -> Unit,
    onClickAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(
                onClick = { onClickNavigation() },
                content = {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = null
                    )
                }
            )
        },
        actions = {
            IconButton(
                onClick = { onClickAction() },
                content = {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = null
                    )
                }
            )
        },
        modifier = modifier
    )
}

@Composable
@Preview(showBackground = true)
fun PreviewTopAppBarDefault() {
    TopAppBarDefault(title = "App Name")
}

@Composable
@Preview(showBackground = true)
fun PreviewTopAppBarWithNavigation() {
    TopAppBarWithNavigation(title = "App Name", onClickNavigation = { /*TODO*/ })
}

@Composable
@Preview(showBackground = true)
fun PreviewTopAppBarWithAction() {
    TopAppBarWithAction(title = "App Name", onClickAction = { /*TODO*/ })
}

@Composable
@Preview(showBackground = true)
fun PreviewTopAppBarWithNavigationAndAction() {
    TopAppBarWithNavigationAndAction(
        title = "App Name",
        onClickNavigation = { /*TODO*/ },
        onClickAction = { /*TODO*/ })
}