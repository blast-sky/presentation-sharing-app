package me.astrog.presentationsharing.ui.composable.upload

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

private const val pptxMimoType = "application/vnd.openxmlformats-officedocument.presentationml.presentation"

@Composable
fun UploadPresentation(
    onAcceptPptx: (pptxUri: Uri) -> Unit,
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { pptxUri ->
        if(pptxUri == null) {
            return@rememberLauncherForActivityResult
        }

        onAcceptPptx(pptxUri)
    }

    Button(onClick = { launcher.launch(arrayOf(pptxMimoType)) }) {
        Text(text = "Выбрать презентацию")
    }
}

