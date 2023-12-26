package me.astrog.presentationsharing.ui.navigating

sealed class RoomScreens(
    val route: String,
    val routeWithoutArgs: String,
) {

    constructor(route: String) : this(route, route)

    data object UploadFile : RoomScreens("room-upload-file")
    data object ShareFile : RoomScreens("room-share-file/{pptxUri}", "room-share-file")
}