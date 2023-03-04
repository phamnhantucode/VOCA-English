package com.phamnhantucode.vocaenglish.ui.presentation

import android.content.Intent
import android.view.RoundedCorner
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.phamnhantucode.vocaenglish.R
import com.phamnhantucode.vocaenglish.ui.activities.HomeActivity
import com.phamnhantucode.vocaenglish.ui.theme.*
import com.phamnhantucode.vocaenglish.ui.viewmodels.AuthViewModel
import timber.log.Timber

@Composable
fun AuthScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel(),
) {
    val isNetworkAvailable by remember {
        viewModel.isNetworkAvailable
    }
    val authState =
        viewModel.authState.collectAsState(initial = null) //remember look at this function description
    val context = LocalContext.current
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        if (!isNetworkAvailable) {
            AlertDialog(
                title = {
                    Text("Warning")
                },
                text = {
                    Text("Network connection has some problem")
                },
                onDismissRequest = {

                },
                confirmButton = {
                    viewModel.isNetworkAvailable()
                }
            )
        }
        if (authState.value?.loading == false && !authState.value?.error.equals("")) {
            AlertDialog(
                confirmButton = {
                    Button(onClick = {
                        viewModel.resetAuthState()
                    }) {
                        Text(text = "OK")
                    }
                },
                title = {
                    Text("Warning")
                },
                text = {
                    Text(authState.value?.error.toString())
                },
                onDismissRequest = {
                    viewModel.resetAuthState()
                }

            )
        }
        if (authState.value?.loading == true) {
            DialogBoxLoading()
        }
        Timber.d("it goes her1e")
        LaunchedEffect(key1 = authState.value) {

            Timber.d(authState.value.toString())
            authState.value?.success?.let {
                Timber.d("it goes here3")
                context.startActivity(Intent(context, HomeActivity::class.java).apply {
                    putExtra("email", authState.value?.success?.user?.email)
                })
            }

        }
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
            AuthSection(
                navController = navController,
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
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val result = account.getResult(ApiException::class.java)
                val credentials = GoogleAuthProvider.getCredential(result.idToken, null)
                viewModel.googleSignIn(credentials)
            } catch (it: ApiException) {
                print(it)
            }
        }
    val focusRequester = remember { FocusRequester() }
    val localFocusManager =
        LocalFocusManager.current
    val isEmailValid by remember {
        viewModel.isEmailValid
    }
    val isPasswordValid by remember {
        viewModel.isPasswordValid
    }
    val isEmailOrPasswordEmpty by remember {
        viewModel.isEmailOrPasswordEmpty
    }
    var password by rememberSaveable() {
        mutableStateOf("")
    }
    var email by rememberSaveable() {
        mutableStateOf("")
    }
    Column(
        modifier = modifier
            .padding(horizontal = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        //email text field
        Column() {
            Text(
                text = stringResource(id = R.string.email_address),
                style = TextStyle(
                    fontFamily = shanTellSansFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp
                )
            )

            OutlinedTextField(
                value = email,
                maxLines = 1,
                onValueChange = {
                    email = it
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Email
                ),
                keyboardActions = KeyboardActions(onDone = {
                    focusRequester.requestFocus()
                }),
                textStyle = MaterialTheme.typography.body1,
                modifier = Modifier
                    .fillMaxWidth(),
                shape = CircleShape,
                placeholder = {
                    Text(text = "abcd.ef@gmail.com")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.MailOutline,
                        contentDescription = null,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                },
                isError = !isEmailValid || isEmailOrPasswordEmpty
            )
        }
        if (!isEmailValid) {
            Text(
                text = stringResource(id = R.string.email_error),
                style = ErrorTextStyle,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
        //password text field
        Column() {
            Text(
                text = stringResource(id = R.string.passwordField),
                style = TextStyle(
                    fontFamily = shanTellSansFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp
                )
            )

            var passwordVisibility by remember {
                mutableStateOf(false)
            }

            val icon = if (passwordVisibility) {
                painterResource(id = R.drawable.ic_baseline_visibility_24)
            } else {
                painterResource(id = R.drawable.ic_baseline_visibility_off_24)
            }
            OutlinedTextField(
                value = password,
                maxLines = 1,
                onValueChange = {
                    password = it
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password
                ),
                keyboardActions = KeyboardActions(onDone = {
                    localFocusManager.clearFocus()
                }),
                textStyle = MaterialTheme.typography.body1,
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                shape = CircleShape,
                placeholder = {
                    Text(text = "min 6 characters")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        Icon(
                            painter = icon,
                            contentDescription = null,
                            modifier = Modifier.padding(end = 5.dp)
                        )
                    }
                },
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                isError = isEmailOrPasswordEmpty || !isPasswordValid

            )
            if (!isPasswordValid) {
                Text(
                    text = stringResource(id = R.string.password_error),
                    style = ErrorTextStyle
                )
            }
            if (isEmailOrPasswordEmpty) {
                Text(
                    text = stringResource(id = R.string.email_or_password_empty),
                    style = ErrorTextStyle,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                viewModel.checkEmailAndPasswordValid(email = email, password = password)
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
        val token = stringResource(id = R.string.default_web_client_id)
        val context = LocalContext.current
        Button(
            onClick = {
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .requestIdToken(token)
                    .build()
                val googleSignInClient = GoogleSignIn.getClient(context, gso)
                launcher.launch(googleSignInClient.signInIntent)
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