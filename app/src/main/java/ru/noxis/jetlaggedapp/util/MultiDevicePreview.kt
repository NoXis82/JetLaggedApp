package ru.noxis.jetlaggedapp.util

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "small font",
    group = "font scales",
    fontScale = 0.5f
)
@Preview(
    name = "large font",
    group = "font scales",
    fontScale = 1.5f
)
annotation class FontScalePreviews

@Preview(showBackground = true)
@Preview(device = "spec:width=1280dp,height=800dp,dpi=240", showBackground = true)
@Preview(device = "spec:width=673dp,height=841dp", showBackground = true)
@Preview(device = Devices.PIXEL_2)
annotation class MultiDevicePreview