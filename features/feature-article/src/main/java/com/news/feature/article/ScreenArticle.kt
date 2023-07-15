package com.news.feature.article

import android.text.TextUtils
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.google.android.material.textview.MaterialTextView
import com.news.data.article.model.Article
import com.news.ui.component.CommonLoading
import com.news.ui.component.CommonLoadingPagination
import com.news.ui.component.CommonMessage

@Composable
fun ScreenArticle(
    source: String,
    modifier: Modifier = Modifier,
    onClickArticle: (url: String) -> Unit,
    viewModel: ArticleViewModel = viewModel()
) {
    val articles = viewModel.getArticles(source).collectAsLazyPagingItems()
    ArticleList(
        articles = articles,
        onClickArticle = { onClickArticle(it) },
        modifier = modifier
    )
}

@Composable
fun ArticleList(
    articles: LazyPagingItems<Article>,
    modifier: Modifier = Modifier,
    onClickArticle: (url: String) -> Unit,
) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        items(count = articles.itemCount) { index ->
            val article = articles[index]
            article?.let {
                ArticleItem(
                    article = it,
                    onClickArticle = { onClickArticle(it) }
                )
                Divider(color = Color.LightGray, modifier = Modifier.padding(horizontal = 20.dp))
            }
        }

        /* Handle First Load */
        when (val state = articles.loadState.refresh) {
            is LoadState.Error -> {
                Log.d("Screen Article", state.error.localizedMessage ?: "")
                item {
                    CommonMessage(message = stringResource(id = com.news.ui.R.string.common_error_message))
                }
            }

            is LoadState.Loading -> {
                item {
                    Column(
                        modifier = Modifier
                            .fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        CommonLoading()
                    }
                }
            }

            else -> {}
        }

        /* Pagination */
        when (val state = articles.loadState.append) {
            is LoadState.Error -> {
                Log.d("Screen Article", state.error.localizedMessage ?: "")
                item {
                    CommonMessage(message = stringResource(id = com.news.ui.R.string.common_error_message))
                }
            }

            is LoadState.Loading -> { // Pagination Loading UI
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        CommonLoadingPagination()
                    }
                }
            }

            else -> {}
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