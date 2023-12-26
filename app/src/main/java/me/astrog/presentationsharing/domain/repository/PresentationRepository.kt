package me.astrog.presentationsharing.domain.repository

import android.net.Uri
import me.astrog.presentationsharing.domain.model.Slide

interface PresentationRepository {

    fun getSlide(index: Long, presentationUri: Uri): Slide

    fun getSlideCount(presentationUri: Uri): Long
}