package template.shared

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PathEffect
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGContextSetShadow
import platform.CoreGraphics.CGSizeMake
import platform.UIKit.UIGraphicsGetCurrentContext

@OptIn(ExperimentalForeignApi::class)
actual fun drawShadowRect(
    shadowRadius: Float,
    color: Color,
    offsetX: Float,
    offsetY: Float,
    canvas: Canvas,
    size: Size,
) {
    val context = UIGraphicsGetCurrentContext()
    if (context != null) {
        CGContextSetShadow(
            context,
            CGSizeMake(offsetX.toDouble(), offsetY.toDouble()),
            shadowRadius.toDouble(),
        )
    }

    val paint = Paint().apply {
        this.color = color
        this.pathEffect = PathEffect.cornerPathEffect(shadowRadius)
    }

    canvas.drawRoundRect(
        left = 0f,
        top = 0f,
        right = size.width,
        bottom = size.height,
        radiusX = shadowRadius,
        radiusY = shadowRadius,
        paint = paint,
    )
}
