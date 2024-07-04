package pro.inc.recipeapp


//sealed class is a class that can have subclasses but cannot be instantiated and it is used to represent restricted class hierarchies
sealed class Screen(val route: String){
    object RecipeScreen: Screen("recipescreen")
    object DetailScreen: Screen("detailscreen")


}