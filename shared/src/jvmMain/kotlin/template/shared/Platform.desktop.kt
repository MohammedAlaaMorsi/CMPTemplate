package template.shared

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import kotlin.math.pow

class JVMPlatform : Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()

actual fun drawShadowRect(
    shadowRadius: Float,
    color: Color,
    offsetX: Float,
    offsetY: Float,
    canvas: Canvas,
    size: Size,
) {
    val paint = Paint()
    val layers = 50 // More layers = smoother shadow
    val maxAlpha = 0.05f // Control max shadow opacity (adjustable)

    for (i in 0 until layers) {
        // Non-linear opacity falloff (quadratic)
        val alphaFactor = ((layers - i).toFloat() / layers).pow(2) * maxAlpha
        paint.color = color.copy(alpha = alphaFactor)

        // Non-linear spread for softer edges
        val spreadFactor = (i.toFloat() / layers).pow(1.5f) * shadowRadius

        canvas.drawRoundRect(
            left = -spreadFactor + offsetX,
            top = -spreadFactor + offsetY,
            right = size.width + spreadFactor + offsetX,
            bottom = size.height + spreadFactor + offsetY,
            radiusX = shadowRadius + spreadFactor / 2,
            radiusY = shadowRadius + spreadFactor / 2,
            paint = paint,
        )
    }

    // Draw the main rectangle on top
    paint.color = color.copy(alpha = 1f)
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
