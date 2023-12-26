package me.astrog.presentationsharing.ui.composable.upload.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RoomUploadScreenViewModel @Inject constructor(): ViewModel() {

    fun getRoomId(): String {
        return 646464.toString()
    }
}