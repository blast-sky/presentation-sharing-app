package me.astrog.presentationsharing.domain.repository

import kotlinx.coroutines.flow.Flow
import me.astrog.presentationsharing.domain.model.Slide
import me.astrog.presentationsharing.domain.model.SlideImage

interface RoomRepository {

    fun getSlideImage(roomId: String): Flow<SlideImage>

    fun updateSlide(slide: Slide, roomId: String)
}