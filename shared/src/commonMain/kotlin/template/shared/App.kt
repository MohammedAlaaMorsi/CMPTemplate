package template.shared

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import template.shared.ui.UiImage
import template.shared.ui.components.ImageWrapper
import template.shared.ui.theme.TemplateTheme

@Preview
@Composable
fun App() {
    TemplateTheme {
        var showContent by remember {
            mutableStateOf(false)
        }

        val shadowRadius = with(LocalDensity.current) { 16.dp.toPx() }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Button(
                modifier = Modifier.padding(16.dp),
                onClick = {
                    showContent = !showContent
                },
            ) {
                Text(
                    text = "Click me!",
                )
            }

            AnimatedVisibility(showContent) {
                val greeting = remember {
                    Greeting().greet()
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.padding(32.dp).wrapContentSize().drawBehind {
                            drawShadowRect(
                                shadowRadius = shadowRadius,
                                color = Color.Green.copy(alpha = 0.5f),
                                offsetX = 0f,
                                offsetY = 0f,
                                canvas = drawContext.canvas,
                                size = size,
                            )
                        },
                    ) {
                        ImageWrapper(
                            image = UiImage.Local(Res.drawable.compose_multiplatform),
                            contentDescription = null,
                            modifier = Modifier.wrapContentSize().clip(RoundedCornerShape(shadowRadius)).background(Color.White),
                        )
                    }

                    Text(
                        text = "Compose: $greeting",
                    )
                }
            }
        }
    }
}
