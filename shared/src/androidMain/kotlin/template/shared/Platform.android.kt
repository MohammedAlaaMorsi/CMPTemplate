package template.shared

import android.graphics.Bitmap
import android.graphics.BlurMaskFilter
import android.graphics.Paint
import android.os.Build
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
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
    val nativeCanvas = canvas.nativeCanvas

    // Create an offscreen bitmap to render the shadow
    val shadowBitmap = Bitmap.createBitmap(
        (size.width + shadowRadius * 2).toInt(),
        (size.height + shadowRadius * 2).toInt(),
        Bitmap.Config.ARGB_8888,
    )
    val shadowCanvas = android.graphics.Canvas(shadowBitmap)

    val shadowPaint = android.graphics.Paint().apply {
        style = android.graphics.Paint.Style.FILL
        this.color = color.toArgb()
        maskFilter = BlurMaskFilter(shadowRadius, BlurMaskFilter.Blur.NORMAL)
    }

    // Draw shadow onto the offscreen bitmap
    shadowCanvas.drawRoundRect(
        shadowRadius + offsetX,
        shadowRadius + offsetY,
        size.width + shadowRadius + offsetX,
        size.height + shadowRadius + offsetY,
        shadowRadius,
        shadowRadius,
        shadowPaint,
    )

    // Draw the shadow bitmap onto the main canvas
    nativeCanvas.drawBitmap(shadowBitmap, -shadowRadius, -shadowRadius, null)

    // Draw the actual box
    val boxPaint = Paint().apply {
        style = Paint.Style.FILL
        this.color = color.toArgb() // Change to your desired box color
    }

    nativeCanvas.drawRoundRect(
        0f,
        0f,
        size.width,
        size.height,
        shadowRadius,
        shadowRadius,
        boxPaint,
    )
}
