package me.astrog.presentationsharing.ui.composable.share

import me.astrog.presentationsharing.domain.model.Slide

sealed class RoomShareScreenState {

    data class Ready(
        val currentSlide: Slide,
        val roomId: String,
        val slideCount: Long,
    ) : RoomShareScreenState()

    data object Empty : RoomShareScreenState()
}
