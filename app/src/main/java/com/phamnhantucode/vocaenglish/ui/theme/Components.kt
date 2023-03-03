package com.phamnhantucode.vocaenglish.ui.theme

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(
    hintText: String,
    maxLine: Int = 1,
    modifier: Modifier = Modifier,
    textStyle: TextStyle,
    onValueChange: (String) -> Unit
) {
    var value by remember {
        mutableStateOf("")
    }
    BasicTextField(
        value = value,
        maxLines = maxLine,
        onValueChange = {
            value = it
            onValueChange(it)
        },
        textStyle = textStyle,
        decorationBox = {
            Box(
                modifier = Modifier
                    // margin left and right
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = CircleShape
                    )
                    .padding(horizontal = 16.dp, vertical = 12.dp), // inner padding
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = hintText,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.LightGray
                    )
                }
                it()
            }
        }
    )
}