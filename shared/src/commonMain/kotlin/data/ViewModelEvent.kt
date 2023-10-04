package data

sealed class ViewModelEvent {
    data class OpenPostContextMenu(val postId: String) : ViewModelEvent()

    data class OpenComments(val postId: String) : ViewModelEvent()

}