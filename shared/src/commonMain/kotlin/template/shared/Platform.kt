package template.shared

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

@Suppress("LongParameterList")
expect fun drawShadowRect(
    shadowRadius: Float,
    color: Color,
    offsetX: Float,
    offsetY: Float,
    canvas: Canvas,
    size: Size,
)
