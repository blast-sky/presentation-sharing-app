package me.astrog.presentationsharing.ui.composable.share

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import me.astrog.presentationsharing.domain.model.Slide
import me.astrog.presentationsharing.ui.composable.common.RoomLabel
import me.astrog.presentationsharing.ui.composable.share.viewmodel.RoomShareScreenViewModel


@Composable
fun RoomShareScreen(viewModel: RoomShareScreenViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val state by viewModel.state
        val cachedState = state
        
        if(cachedState !is RoomShareScreenState.Ready) {
            return
        }
        
        RoomLabel(roomId = cachedState.roomId)
        Box(contentAlignment = Alignment.TopEnd) {
            Base64Image(base64String = cachedState.currentSlide.imageBase64)
            Text(text = "${cachedState.currentSlide.index}/${cachedState.slideCount}")
        }
        LeftRightButtons(
            onLeft = viewModel::decreaseSlide,
            onRight = viewModel::increaseSlide,
        )
    }
}

@Preview(backgroundColor = 1, showSystemUi = true)
@Composable
fun RoomShareScreenPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val state = RoomShareScreenState.Ready(
            Slide(2, "123", "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"),
            "123123",
            24
        )
        val cachedState = state

        if(cachedState !is RoomShareScreenState.Ready) {
            return
        }

        RoomLabel(roomId = cachedState.roomId)
        Box(contentAlignment = Alignment.TopEnd) {
            Base64Image(base64String = cachedState.currentSlide.imageBase64)
            Text(text = "${cachedState.currentSlide.index}/${cachedState.slideCount}")
        }
        LeftRightButtons(
            onLeft = { },
            onRight = {},
        )
    }
}

