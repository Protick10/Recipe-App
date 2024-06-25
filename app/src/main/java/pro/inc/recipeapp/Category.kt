package pro.inc.recipeapp

data class Category(val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
    )


//this categoryresponse is the entire json file(object) that we get from the API
data class CategoriesResponse(val categories: List<Category>)