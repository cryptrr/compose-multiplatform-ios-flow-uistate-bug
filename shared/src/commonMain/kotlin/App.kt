import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import data.ViewModelEvent
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import viewModels.MainViewModel

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterialApi::class)
@Composable
fun App() {

    val viewModel = getViewModel(key = Unit, factory = viewModelFactory { MainViewModel() })

    val currentCommentsSheetDetails  = remember {
        mutableStateOf<String?>(null)
    }

    val commentsBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    val scope = rememberCoroutineScope()


    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest {
            when (it) {
                is ViewModelEvent.OpenComments -> {
                        currentCommentsSheetDetails.value = it.postId
                        commentsBottomSheetState.show()
                }

                else -> {}
            }
        }
    }


    MaterialTheme {
        var greetingText by remember { mutableStateOf("Hello, World!") }
        var showImage by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                greetingText = "Hello, ${getPlatformName()}"
                showImage = !showImage
            }) {
                Text(greetingText)
            }

            //DOES WORK
            Button(onClick = {
                scope.launch {
                    commentsBottomSheetState.show()
                }
            }) {
                Text("Show Bottom Sheet: Works")
            }

            //FIXME: DOES NOT WORK
            ButtonDemo(viewModel)

            AnimatedVisibility(showImage) {
                Image(
                    painterResource("compose-multiplatform.xml"),
                    contentDescription = "Compose Multiplatform icon"
                )
            }
        }

        CommentsBottomSheet(commentsBottomSheetState, currentCommentsSheetDetails.value)



    }
}

@Composable
fun ButtonDemo(viewModel: MainViewModel){

    Button(onClick = {
        viewModel.dispatchEvent(ViewModelEvent.OpenComments("Bruh moment"))
    }) {
        Text("Show Bottom Sheet: Not Working")
    }
}


expect fun getPlatformName(): String