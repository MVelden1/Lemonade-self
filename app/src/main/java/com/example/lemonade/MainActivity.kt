package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}


@Preview
@Composable
fun LemonadeApp() {
    LemonadeLifeSpan(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )

}

@Composable
fun LemonadeLifeSpan(modifier: Modifier = Modifier) {
    var result by remember { mutableIntStateOf(1) }


    if (result > 5) {
        result = 1
    }

    val imageResource = when (result) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        5 -> R.drawable.rico
        else -> R.drawable.lemon_restart
    }

    val stringResource = when (result) {
        1 -> R.string.lemon_tree_text
        2 -> R.string.lemon_text
        3 -> R.string.glass_of_lemonade_text
        5 -> R.string.Rico
        else -> R.string.empty_glass_text
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth(),
        contentAlignment = Alignment.TopCenter,
    ) {

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .height(28.dp)
                .background(Color.Yellow),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(R.string.app_name),
                fontSize = 20.sp
            )
        }

        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = imageResource.toString(),
                modifier = Modifier
                    .size(320.dp)
                    .clickable {
                        val randomNumber =
                            Random.nextInt(10) // Genereer een willekeurig getal tussen 0 en 9
                        if (randomNumber == 0) { // 1 op de 10 kans om 5 toe te voegen
                            result = 5
                        } else {
                            if (result < 4) {
                                result++
                            } else {
                                result = 1
                            }
                        }
                    }
                    .background(Color.LightGray)
            )

            Text(
                text = result.toString(),
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                stringResource(stringResource),
                fontSize = 16.sp
            )
        }
        if (result == 5) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            ) {
                Text(
                    text = "Kusjes Mick",
                    fontSize = 16.sp
                )
            }
        }
    }
}



