package com.phamnhantucode.vocaenglish.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.phamnhantucode.vocaenglish.R

val shanTellSansFamily = FontFamily(
    listOf(
        Font(R.font.shantellsans_bold, FontWeight.Bold, FontStyle.Normal),
        Font(R.font.shantellsans_bolditalic, FontWeight.Bold, FontStyle.Italic),
        Font(R.font.shantellsans_italic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.shantellsans_light, FontWeight.Light, FontStyle.Normal),
        Font(R.font.shantellsans_extrabold, FontWeight.ExtraBold, FontStyle.Normal),
        Font(R.font.shantellsans_lightitalic, FontWeight.Light, FontStyle.Italic),
        Font(R.font.shantellsans_extrabolditalic, FontWeight.ExtraBold, FontStyle.Italic),
        Font(R.font.shantellsans_medium, FontWeight.Medium, FontStyle.Normal),
        Font(R.font.shantellsans_mediumitalic, FontWeight.Medium, FontStyle.Italic),
        Font(R.font.shantellsans_semibold, FontWeight.SemiBold, FontStyle.Normal),
        Font(R.font.shantellsans_semibolditalic, FontWeight.SemiBold, FontStyle.Italic),
        Font(R.font.shantellsans_regular, FontWeight.Normal, FontStyle.Normal),
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = shanTellSansFamily,
        fontWeight = FontWeight.ExtraBold,
        fontStyle = FontStyle.Normal,
        fontSize = 32.sp
    ),
    h2 = TextStyle(
        fontFamily = shanTellSansFamily,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        fontSize = 24.sp
    ),
    h3 = TextStyle(
        fontFamily = shanTellSansFamily,
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle.Normal,
        fontSize = 22.sp
    ),
    body1 = TextStyle(
        fontFamily = shanTellSansFamily,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        fontSize = 18.sp,
        lineHeight = 18.sp,
    ),

    body2 = TextStyle(
        fontFamily = shanTellSansFamily,
        fontWeight = FontWeight.Light,
        fontStyle = FontStyle.Normal,
        fontSize = 16.sp,
        lineHeight = 18.sp,
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val ErrorTextStyle = TextStyle(
    fontFamily = shanTellSansFamily,
    fontWeight = FontWeight.Light,
    fontSize = 14.sp,
    color = Color.Red
)