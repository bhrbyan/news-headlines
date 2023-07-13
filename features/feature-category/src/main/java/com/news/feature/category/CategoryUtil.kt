package com.news.feature.category

import android.content.Context
import com.news.data.category.model.Category

object CategoryUtil {

    fun getCategories(context: Context): List<Category> {
        return listOf(
            Category.Business(context.getString(R.string.category_business)),
            Category.Entertainment(context.getString(R.string.category_entertainment)),
            Category.General(context.getString(R.string.category_general)),
            Category.Health(context.getString(R.string.category_health)),
            Category.Science(context.getString(R.string.category_science)),
            Category.Sports(context.getString(R.string.category_sports)),
            Category.Technology(context.getString(R.string.category_technology)),
        )
    }

}