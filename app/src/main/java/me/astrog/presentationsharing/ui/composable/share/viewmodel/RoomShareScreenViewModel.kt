package me.astrog.presentationsharing.ui.composable.share.viewmodel

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import me.astrog.presentationsharing.domain.model.SlideImage
import me.astrog.presentationsharing.domain.repository.PresentationRepository
import me.astrog.presentationsharing.domain.repository.RoomIdWrapper
import me.astrog.presentationsharing.domain.repository.RoomRepository
import me.astrog.presentationsharing.ui.composable.share.RoomShareScreenState
import javax.inject.Inject

private const val TAG = "RoomShareScreenViewModel_TAG"

@HiltViewModel
class RoomShareScreenViewModel @Inject constructor(
    private val presentationRepository: PresentationRepository,
    private val roomIdWrapper: RoomIdWrapper,
    private val roomRepository: RoomRepository,
) : ViewModel() {

    private lateinit var pptxUri: Uri

    val state: MutableState<RoomShareScreenState> = mutableStateOf(RoomShareScreenState.Empty)

    fun initPptxUri(pptxUri: String) {
        this.pptxUri = pptxUri.toUri()
        state.value = RoomShareScreenState.Ready(
            currentSlide = presentationRepository.getSlide(0, this.pptxUri),
            roomId = roomIdWrapper.getRoomId(),
            slideCount = presentationRepository.getSlideCount(this.pptxUri),
        )
    }

    fun getRoomSlideImage(): Flow<SlideImage> = roomRepository.getSlideImage(
        roomId = roomIdWrapper.getRoomId()
    )

    fun increaseSlide() {
        val roomShareScreenState = state.value
        if (roomShareScreenState !is RoomShareScreenState.Ready) {
            return
        }
        if (roomShareScreenState.currentSlide.index >=
            presentationRepository.getSlideCount(pptxUri) - 1
        ) {
            return
        }
        setSlide(roomShareScreenState.currentSlide.index + 1)
    }

    fun decreaseSlide() {
        val roomShareScreenState = state.value
        if (roomShareScreenState !is RoomShareScreenState.Ready) {
            return
        }
        if (roomShareScreenState.currentSlide.index <= 0) {
            return
        }
        setSlide(roomShareScreenState.currentSlide.index - 1)
    }

    private fun setSlide(index: Long) {
        val roomShareScreenState = state.value
        if (roomShareScreenState !is RoomShareScreenState.Ready) {
            Log.e(TAG, "setSlide with empty slide")
            return
        }

        val nextSlide = presentationRepository.getSlide(
            index = roomShareScreenState.currentSlide.index + 1,
            presentationUri = pptxUri,
        )

        roomRepository.updateSlide(nextSlide, roomIdWrapper.getRoomId())
        state.value = roomShareScreenState.copy(currentSlide = nextSlide)
    }
}