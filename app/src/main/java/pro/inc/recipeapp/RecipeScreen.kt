package pro.inc.recipeapp

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeScreen(modifier: Modifier = Modifier){
    val recipeViewModel: MainViewModel = viewModel()
    val viewstate by recipeViewModel.categoryState

    Box(modifier = Modifier.fillMaxSize()){
        when{
            viewstate.loading -> {
                // Show a loading indicator
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }

            viewstate.error != null ->{
                Text(text = "Error: ${viewstate.error}")
            }
            else ->{
                // Show the list of categories
                CategoryScreen(categories = viewstate.list)

            }

        }
    }

}

@Composable
fun CategoryScreen(categories: List<Category>){
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()){
        items(categories){
             category ->
            CategoryItem(category = category)
        }

    }


}

//how each item in the list will look like
@Composable
fun CategoryItem(category: Category){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        //Image is a composable that takes a painter as a parameter. We can use the rememberAsyncImagePainter() function to load the image from the URL

        androidx.compose.foundation.Image(painter = rememberAsyncImagePainter(category.strCategoryThumb), contentDescription =null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f) //aspect ratio is 1:1 of width and height
        )



        Text(text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
            modifier = Modifier.padding(top = 8.dp)
        )

    }
}