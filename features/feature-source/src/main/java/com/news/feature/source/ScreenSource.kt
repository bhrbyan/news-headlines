package com.news.feature.source

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.news.data.source.model.Source

@Composable
fun ScreenSource(
    category: String,
    onClickSource: (id: String) -> Unit,
    sourceViewModel: SourceViewModel = viewModel()
) {
    val sourcesState = sourceViewModel.sources.collectAsState()

    fun init() {
        sourceViewModel.getSources(category)
    }

    init()
    when (val result = sourcesState.value) {
        is SourceViewState.Loading -> {
            Loading()
        }

        is SourceViewState.Success -> {
            SourceList(sources = result.sources, onClickSource = { onClickSource(it) })
        }

        is SourceViewState.Error -> {
            // TODO
        }
    }
}

@Composable
fun Loading(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(48.dp, 48.dp)
        )
    }
}

@Composable
fun SourceList(sources: List<Source>, onClickSource: (id: String) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(sources) { source ->
            SourceItem(source = source, onClickSource = { onClickSource(it) })
            Divider(color = Color.LightGray, modifier = Modifier.padding(horizontal = 20.dp))
        }
    }
}

@Composable
fun SourceItem(
    source: Source, onClickSource: (id: String) -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)
        .clickable {
            onClickSource(source.id)
        }) {
        Text(
            text = source.name, modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Rounded.KeyboardArrowRight, contentDescription = null
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewLoading() {
    Loading()
}

@Composable
@Preview(showBackground = true)
fun PreviewSourceList() {
    SourceList(sources = listOf(
        Source("business", "Business", "", "", "", "", ""),
        Source("sports", "Sports", "", "", "", "", ""),
        Source("technology", "Technology", "", "", "", "", ""),
    ), onClickSource = {})
}

@Composable
@Preview(showBackground = true)
fun PreviewSourceItem() {
    SourceItem(source = Source("business", "Business", "", "", "", "", ""), onClickSource = {})
}