package com.phamnhantucode.vocaenglish.ui.screens

import android.widget.Space
import androidx.compose.animation.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.phamnhantucode.vocaenglish.R
import com.phamnhantucode.vocaenglish.ui.theme.DarkWhite
import com.phamnhantucode.vocaenglish.ui.theme.Teal200
import timber.log.Timber

@Composable
fun WelcomeScreen() {
    Timber.d("It goes to welcomeScreen")
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize()
        ) {
            var maxHeight by remember {
                mutableStateOf(maxHeight)
            }
//            val animation = remember {
//                Animatable(0.45f)
//            }
            //Background
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.45f)
                    .clip(RoundedCornerShape(0.dp, 0.dp, 85.dp, 85.dp))
                    .background(Teal200)
                    .align(Alignment.TopCenter)
            ) {

            }

            //Logo
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = this@BoxWithConstraints.maxHeight * 0.38f)
                        .clip(CircleShape)
                        .align(Alignment.TopCenter)
                        .background(DarkWhite)
                        .padding(20.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo), contentDescription = null,
                        modifier = Modifier
                            .height(60.dp)
                            .aspectRatio(1f)

                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(maxHeight * 0.48f)
                    .align(Alignment.BottomCenter),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.h1
                    )
                    Spacer(modifier = Modifier.height(maxHeight * 0.1f))
                    Text(
                        text = stringResource(id = R.string.introText),
                        style = MaterialTheme.typography.body1
                    )

                    Spacer(modifier = Modifier.height(40.dp))
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .align(CenterHorizontally),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Teal200
                        ),
                        shape = CircleShape

                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Get Start",
                                modifier = Modifier.align(Center),
                                style = MaterialTheme.typography.body1
                            )
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null,
                                modifier = Modifier.align(CenterEnd)
                            )
                        }
                    }
                }
            }

        }


    }
}