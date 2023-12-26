package me.astrog.presentationsharing.ui.composable.upload

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import me.astrog.presentationsharing.ui.composable.common.RoomLabel
import me.astrog.presentationsharing.ui.composable.upload.viewmodel.RoomUploadScreenViewModel


@Composable
fun RoomUploadScreen(
    onAcceptPptx: (pptxUri: Uri) -> Unit,
    viewModel: RoomUploadScreenViewModel,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        RoomLabel(roomId = viewModel.getRoomId())
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            UploadPresentation(onAcceptPptx = onAcceptPptx)
        }
    }
}

@Preview(backgroundColor = 1, showSystemUi = true)
@Composable
fun RoomUploadScreenPreview() {
    RoomUploadScreen(onAcceptPptx = {}, viewModel = RoomUploadScreenViewModel())
}

