package viewModels

import co.touchlab.kermit.Logger
import data.ViewModelEvent
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch


open class MainViewModel(
) : ViewModel() {


    private val _eventFlow = MutableSharedFlow<ViewModelEvent>(replay = 0)
    val eventFlow = _eventFlow.asSharedFlow()


    fun dispatchEvent(event: ViewModelEvent) {

        Logger.i{"Main ViewModel Event: ${event.toString()}"}
        viewModelScope.launch(Dispatchers.Main) {

            _eventFlow.emit(event)
        }


    }

}