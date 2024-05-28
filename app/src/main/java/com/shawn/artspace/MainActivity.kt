package com.shawn.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shawn.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout() {
    var imageId by remember {
        mutableIntStateOf(1)
    }
    BoxWithConstraints {
        val isPortrait = maxWidth < maxHeight
        if (isPortrait) {
            VerticalLayout(imageId) { imageId = it }
        } else {
            HorizontalLayout(imageId) { imageId = it }
        }
    }
}

@Composable
fun VerticalLayout(
    imageId: Int,
    onImageChange: (Int) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .padding(24.dp)
            .statusBarsPadding()
            .safeDrawingPadding()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                ArtImageView(imageId)
            }
            Spacer(modifier = Modifier.height(24.dp))
            ArtInformation(imageId, modifier = Modifier.fillMaxWidth())
        }
        Spacer(modifier = Modifier.height(24.dp))
        Operations(
            imageId,
            onImageChange = onImageChange,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun HorizontalLayout(
    imageId: Int,
    onImageChange: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .statusBarsPadding()
            .safeDrawingPadding()
    ) {
        ArtImageView(imageId)
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .padding(24.dp)
                .statusBarsPadding()
                .safeDrawingPadding()
        ) {
            ArtInformation(imageId, Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(24.dp))
            Operations(
                imageId,
                onImageChange = onImageChange,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun ArtImageView(
    id: Int,
    modifier: Modifier = Modifier
) {
    val imageRes = when (id) {
        1 -> R.drawable.img01
        2 -> R.drawable.img02
        3 -> R.drawable.img03
        4 -> R.drawable.img04
        5 -> R.drawable.img05
        else -> R.drawable.img01
    }
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        shape = RectangleShape,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = null,
            modifier = Modifier.padding(24.dp)
        )
    }
}

@Composable
fun ArtInformation(
    id: Int,
    modifier: Modifier = Modifier
) {
    val informationArray = stringArrayResource(when (id) {
        1 -> R.array.img01
        2 -> R.array.img02
        3 -> R.array.img03
        4 -> R.array.img04
        5 -> R.array.img05
        else -> R.array.img01
    })
    Column(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
            .padding(12.dp)
    ) {
        Text(
            text = informationArray[0],
            textAlign = TextAlign.Start,
            fontSize = 24.sp,
            fontWeight = FontWeight(weight = 300)
        )
        Row {
            Text(
                text = informationArray[1],
                fontWeight = FontWeight(weight = 500)
            )
            Text(
                text = "(${informationArray[2]})",
                fontWeight = FontWeight(weight = 300)
            )
        }
    }
}

@Composable
fun Operations(
    id: Int,
    onImageChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { onImageChange(id - 1) },
            enabled = id > 1,
            modifier = Modifier.width(150.dp)
        ) {
            Text(text = "Previous")
        }
        Button(
            onClick = { onImageChange(id + 1) },
            enabled = id < 5,
            modifier = Modifier.width(150.dp)
        ) {
            Text(text = "Next")
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:shape=Normal,width=800,height=1080,unit=dp,dpi=420"
)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}
@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=420"
)
@Composable
fun ArtSpacePreview2() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}