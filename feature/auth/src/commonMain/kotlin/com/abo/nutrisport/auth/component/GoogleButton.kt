package com.abo.nutrisport.auth.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abo.nutrisport.FontSize
import com.abo.nutrisport.Gray
import com.abo.nutrisport.GrayDarker
import com.abo.nutrisport.IconSecondary
import com.abo.nutrisport.Resources
import com.abo.nutrisport.TextPrimary
import kotlinx.serialization.EncodeDefault
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun GoogleButton(
    modifier: Modifier = Modifier,
    loadingState: Boolean = false,
    primaryText: String = "SignIn with Google",
    secondaryText: String = "Please wait...",
    icon: DrawableResource = Resources.Image.GoogleLogo,
    shape: Shape = RoundedCornerShape(size = 99.dp),
    backgroundColor: Color = Gray,
    borderColor: Color = GrayDarker,
    progressIndicatorColor: Color = IconSecondary,
    onCLick: () -> Unit = {},
) {

    var buttonText by remember { mutableStateOf(primaryText) }

    LaunchedEffect(loadingState) {
        buttonText = if (loadingState) secondaryText else primaryText
    }

    Surface(
        modifier = modifier.clip(shape).border(width = 1.dp, color = borderColor, shape = shape),
        onClick = onCLick,
        enabled = !loadingState,
        color = backgroundColor
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(20.dp)
                .animateContentSize(animationSpec = tween(durationMillis = 200)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(
                visible = !loadingState
            ) {
                Icon(painter = painterResource(icon), tint = Color.Unspecified, contentDescription = "Google Logo")
            }
            AnimatedVisibility(
                visible = loadingState,
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = progressIndicatorColor,
                    strokeWidth = 2.dp
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = buttonText, color = TextPrimary, fontSize = FontSize.REGULAR)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGoogleButton() {
    GoogleButton(
        loadingState = false,
        onCLick = {}
    )
}


