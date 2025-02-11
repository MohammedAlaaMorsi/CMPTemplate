package template.shared

import android.graphics.Paint
import android.os.Build
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePaint
import androidx.compose.ui.graphics.toArgb

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

@Suppress("LongParameterList")
actual fun drawShadowRect(
    shadowRadius: Float,
    color: Color,
    offsetX: Float,
    offsetY: Float,
    canvas: Canvas,
    size: Size,
) {
    val paint = Paint().apply {
        style = Paint.Style.FILL
        setShadowLayer(
            shadowRadius,
            offsetX,
            offsetY,
            color.toArgb(),
        )
    }

    canvas.drawRoundRect(
        left = 0f,
        top = 0f,
        right = size.width,
        bottom = size.height,
        radiusX = shadowRadius,
        radiusY = shadowRadius,
        paint.asComposePaint(),
    )
}
