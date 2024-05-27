package com.shawn.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .padding(24.dp)
    ) {
        Column {
            ArtImageView(imageId)
            Spacer(modifier = Modifier.height(36.dp))
            ArtInformation(imageId)
        }
        Operations(
            imageId,
            onImageChange = { imageId = it}
        )
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
        shape = RectangleShape
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = null,
            modifier = modifier.padding(24.dp)
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
            .fillMaxWidth()
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
        modifier = modifier.fillMaxWidth(),
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
    showSystemUi = true
)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}