package com.news.data.category.model

sealed class Category {
    abstract val id: String
    abstract val name: String

    data class Business(override val name: String) : Category() {
        override val id: String
            get() = ID_BUSINESS
    }

    data class Entertainment(override val name: String) : Category() {
        override val id: String
            get() = ID_ENTERTAINMENT
    }

    data class General(override val name: String) : Category() {
        override val id: String
            get() = ID_GENERAL
    }

    data class Health(override val name: String) : Category() {
        override val id: String
            get() = ID_HEALTH
    }

    data class Science(override val name: String) : Category() {
        override val id: String
            get() = ID_SCIENCE
    }

    data class Sports(override val name: String) : Category() {
        override val id: String
            get() = ID_SPORTS
    }

    data class Technology(override val name: String) : Category() {
        override val id: String
            get() = ID_TECHNOLOGY
    }

    companion object {
        private const val ID_BUSINESS: String = "business"
        private const val ID_ENTERTAINMENT: String = "entertainment"
        private const val ID_GENERAL: String = "general"
        private const val ID_HEALTH: String = "health"
        private const val ID_SCIENCE: String = "science"
        private const val ID_SPORTS: String = "sports"
        private const val ID_TECHNOLOGY: String = "technology"
    }
}
