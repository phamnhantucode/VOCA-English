package com.phamnhantucode.vocaenglish.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.phamnhantucode.vocaenglish.R
import com.phamnhantucode.vocaenglish.ui.theme.CustomTextField
import com.phamnhantucode.vocaenglish.ui.theme.DarkWhite
import com.phamnhantucode.vocaenglish.ui.theme.Teal200
import com.phamnhantucode.vocaenglish.ui.theme.shanTellSansFamily
import timber.log.Timber

@Composable
fun AuthScreen(
    navController: NavController
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        BoxWithConstraints(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            val screenHeight by remember {
                mutableStateOf(maxHeight)
            }
            TopBarAuthScreen(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .offset(y = screenHeight * 0.1f)
            )
            AuthSection(navController = navController,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
fun TopBarAuthScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo), contentDescription = null,
                modifier = Modifier
                    .height(50.dp)
                    .aspectRatio(1f)
            )
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.h2
            )
        }
    }
}

@Composable
fun AuthSection(
    isLogin: Boolean = true,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Column() {
            Text(
                text = stringResource(id = R.string.email_address),
                style = TextStyle(
                    fontFamily = shanTellSansFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp
                )
            )
            CustomTextField(
                hintText = stringResource(id = R.string.hint_email_address),
                textStyle = MaterialTheme.typography.body1
            ) {
                Timber.d(it)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Column() {
            Text(
                text = stringResource(id = R.string.email_address),
                style = TextStyle(
                    fontFamily = shanTellSansFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp
                )
            )
            CustomTextField(
                hintText = stringResource(id = R.string.hint_email_address),
                textStyle = MaterialTheme.typography.body1
            ) {
                Timber.d(it)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                navController.navigate("")
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Teal200
            ),
            shape = CircleShape

        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.continue_button_text),
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.body1
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        //Divide line
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .weight(1f)
                    .background(Color.LightGray)
            )
            Text(
                text = "or",
                color = Color.Gray,
                modifier = Modifier
                    .padding(horizontal = 2.dp)
            )
            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .weight(1f)
                    .background(Color.LightGray)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        //social login
        Button(
            onClick = {
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),

            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White
            ),
            shape = CircleShape,
            border = BorderStroke(
                1.dp,
                DarkWhite
            ),
            elevation = ButtonDefaults.elevation(0.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = "Google sign in",
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .height(24.dp)
                    .aspectRatio(1f)
            )
            Text(
                text = stringResource(id = R.string.signin_with_google),
                fontFamily = shanTellSansFamily
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),

            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White
            ),
            shape = CircleShape,
            border = BorderStroke(
                1.dp,
                DarkWhite
            ),
            elevation = ButtonDefaults.elevation(0.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_facebook),
                contentDescription = "Facebook sign in",
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .height(24.dp)
                    .aspectRatio(1f)
            )
            Text(
                text = stringResource(id = R.string.signin_with_facebook),
                fontFamily = shanTellSansFamily
            )
        }
    }
}