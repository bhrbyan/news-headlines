package com.news.feature.category

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.news.data.category.model.Category

@Composable
fun ScreenCategory(categories: List<Category>) {
    Column {
        CategoryTitle(title = stringResource(id = R.string.category_title))
        CategoryList(categories = categories)
    }
}

@Composable
fun CategoryTitle(title: String) {
    Text(
        text = title,
        fontSize = 24.sp,
        modifier = Modifier.padding(start = 20.dp, top = 20.dp)
    )
}

@Composable
fun CategoryList(categories: List<Category>) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(categories) { category ->
            CategoryItem(category.name)
            Divider(color = Color.LightGray, modifier = Modifier.padding(horizontal = 20.dp))
        }
    }
}

@Composable
fun CategoryItem(name: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Text(
            text = name,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Rounded.KeyboardArrowRight,
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreenCategory() {
    ScreenCategory(
        categories = listOf(
            Category.Business("Business"),
            Category.Sports("Sports"),
            Category.Technology("Technology")
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCategoryTitle() {
    CategoryTitle(title = "Categories")
}

@Preview(showBackground = true)
@Composable
fun PreviewCategoryList() {
    CategoryList(
        categories = listOf(
            Category.Business("Business"),
            Category.Sports("Sports"),
            Category.Technology("Technology")
        )
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewCategoryItem() {
    CategoryItem("Business")
}
