package com.news.feature.article

import android.text.TextUtils
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.google.android.material.textview.MaterialTextView
import com.news.data.article.model.Article
import com.news.ui.R
import com.news.ui.component.CommonLoading
import com.news.ui.component.CommonMessage

@Composable
fun ScreenArticle(
    source: String,
    modifier: Modifier = Modifier,
    onClickArticle: (url: String) -> Unit,
    viewModel: ArticleViewModel = viewModel()
) {
    val viewState = viewModel.viewState.collectAsState()

    fun init() {
        viewModel.getArticles(source)
    }

    init()
    when (val result = viewState.value) {
        is ArticleViewState.Loading -> {
            CommonLoading(
                modifier = modifier
            )
        }

        is ArticleViewState.Success -> {
            ArticleList(
                articles = result.sources,
                onClickArticle = { onClickArticle(it) },
                modifier = modifier
            )
        }

        is ArticleViewState.Empty -> {
            CommonMessage(
                message = stringResource(id = R.string.common_empty_message),
                modifier = modifier
            )
        }

        is ArticleViewState.Error -> {
            CommonMessage(
                message = stringResource(id = R.string.common_error_message),
                modifier = modifier
            )
        }
    }
}

@Composable
fun ArticleList(
    articles: List<Article>,
    modifier: Modifier = Modifier,
    onClickArticle: (url: String) -> Unit,
) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        items(articles) { article ->
            ArticleItem(
                article = article,
                onClickArticle = { onClickArticle(it) }
            )
            Divider(color = Color.LightGray, modifier = Modifier.padding(horizontal = 20.dp))
        }
    }
}

@Composable
fun ArticleItem(
    article: Article,
    modifier: Modifier = Modifier,
    onClickArticle: (url: String) -> Unit,
) {
    Column(
        modifier = modifier
            .padding(20.dp)
            .fillMaxWidth()
            .clickable {
                onClickArticle(article.url)
            }
    ) {
        AsyncImage(
            model = article.urlToImage,
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Text(
            text = article.title,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        if (article.description != null) {
            val spannedText =
                HtmlCompat.fromHtml(article.description ?: "", HtmlCompat.FROM_HTML_MODE_LEGACY)

            AndroidView(
                factory = { MaterialTextView(it) },
                update = {
                    it.text = spannedText
                    it.maxLines = 2
                    it.ellipsize = TextUtils.TruncateAt.END
                },
            )
        }

        if (article.author.isNullOrEmpty().not()) {
            Text(
                text = article.author ?: "",
                modifier = Modifier.padding(end = 8.dp, top = 8.dp)
            )
        }
        Text(
            text = article.publishedAt,
            fontWeight = FontWeight.Light,
            fontSize = 12.sp
        )

    }
}

@Composable
@Preview(showBackground = true)
fun PreviewArticleItem() {
    ArticleItem(
        article = Article(
            author = "Alex Handerson",
            title = "Greate Communication",
            description = "At its broadest definition, climate-tech funding is way down, yet there are bright spots for early stage companies and certain sub-sectors.",
            url = "",
            urlToImage = "",
            publishedAt = "2023-07-13T20:39:42Z",
            content = ""
        ),
        onClickArticle = {}
    )
}