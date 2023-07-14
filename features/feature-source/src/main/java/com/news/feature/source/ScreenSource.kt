package com.news.feature.source

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.news.data.source.model.Source
import com.news.ui.R
import com.news.ui.component.CommonLoading
import com.news.ui.component.CommonMessageError

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
            CommonLoading()
        }

        is SourceViewState.Success -> {
            SourceList(sources = result.sources, onClickSource = { onClickSource(it) })
        }

        is SourceViewState.Error -> {
            CommonMessageError(message = stringResource(id = R.string.common_error_message))
        }
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
    source: Source, onClickSource: (id: String) -> Unit, modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(20.dp)
            .fillMaxWidth()
            .clickable {
                onClickSource(source.id)
            }
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = source.name, fontWeight = FontWeight.Bold)
            Text(text = source.description)
        }
        Icon(
            imageVector = Icons.Rounded.KeyboardArrowRight,
            contentDescription = null
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewSourceList() {
    SourceList(sources = listOf(
        Source(
            "business",
            "Business",
            "n Friday, a federal court sentenced Joseph James O’Conner",
            "",
            "",
            "",
            ""
        ),
        Source(
            "sports",
            "Sports",
            "n Friday, a federal court sentenced Joseph James O’Conner",
            "",
            "",
            "",
            ""
        ),
        Source(
            "technology",
            "Technology",
            "n Friday, a federal court sentenced Joseph James O’Conner",
            "",
            "",
            "",
            ""
        ),
    ), onClickSource = {})
}

@Composable
@Preview(showBackground = true)
fun PreviewSourceItem() {
    SourceItem(source = Source(
        "business",
        "Business",
        "n Friday, a federal court sentenced Joseph James O’Conner",
        "",
        "",
        "",
        ""
    ), onClickSource = {})
}