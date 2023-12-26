package me.astrog.presentationsharing.domain.model

data class Slide(
    val index: Long,
    val author: String,
    val imageBase64: String,
)
