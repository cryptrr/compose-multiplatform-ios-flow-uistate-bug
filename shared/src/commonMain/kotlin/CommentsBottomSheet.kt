import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CommentsBottomSheet(
    state: ModalBottomSheetState,
    currentPost: String?,
) {


    val scope = rememberCoroutineScope()



    @Composable
    fun bottomSheetContent() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                //.height(700.dp)
                .fillMaxHeight(0.75F),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
            //TODO: Expect actual implement
            //.windowInsetsPadding(WindowInsets.navigationBars)

        )
        {

            Text("Comments Sheeet")
            Text("Current Post $currentPost")


        }


    }


    ModalBottomSheetLayout(
        sheetState = state,
        sheetContent = { bottomSheetContent() },
        sheetShape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
        sheetBackgroundColor = Color.White


    ) {

    }
}
