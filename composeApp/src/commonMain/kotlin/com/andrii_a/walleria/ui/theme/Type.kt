package com.andrii_a.walleria.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import walleriamultiplatform.composeapp.generated.resources.Res
import walleriamultiplatform.composeapp.generated.resources.open_sans_bold
import walleriamultiplatform.composeapp.generated.resources.open_sans_light
import walleriamultiplatform.composeapp.generated.resources.open_sans_medium
import walleriamultiplatform.composeapp.generated.resources.open_sans_regular
import walleriamultiplatform.composeapp.generated.resources.open_sans_semi_bold
import walleriamultiplatform.composeapp.generated.resources.pacifico_regular

private val Pacifico: FontFamily
    @Composable
    get() {
        return FontFamily(
            Font(Res.font.pacifico_regular)
        )
    }

val OpenSans: FontFamily
    @Composable
    get() {
        return FontFamily(
            Font(Res.font.open_sans_light, FontWeight.Light),
            Font(Res.font.open_sans_regular, FontWeight.Normal),
            Font(Res.font.open_sans_medium, FontWeight.Medium),
            Font(Res.font.open_sans_semi_bold, FontWeight.SemiBold),
            Font(Res.font.open_sans_bold, FontWeight.Bold)
        )
    }

val Typography: Typography
    @Composable
    get() {
        return Typography(
            displayLarge = TextStyle(
                fontFamily = OpenSans,
                fontWeight = FontWeight.Bold,
                fontSize = 57.sp,
                lineHeight = 64.sp,
                letterSpacing = (-0.25).sp
            ),
            displayMedium = TextStyle(
                fontFamily = OpenSans,
                fontWeight = FontWeight.Bold,
                fontSize = 45.sp,
                lineHeight = 52.sp,
                letterSpacing = 0.sp
            ),
            displaySmall = TextStyle(
                fontFamily = OpenSans,
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
                lineHeight = 44.sp,
                letterSpacing = 0.sp
            ),
            headlineLarge = TextStyle(
                fontFamily = OpenSans,
                fontWeight = FontWeight.SemiBold, // Often SemiBold or Bold for headlines
                fontSize = 32.sp,
                lineHeight = 40.sp,
                letterSpacing = 0.sp
            ),
            headlineMedium = TextStyle(
                fontFamily = OpenSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 28.sp,
                lineHeight = 36.sp,
                letterSpacing = 0.sp
            ),
            headlineSmall = TextStyle(
                fontFamily = OpenSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                lineHeight = 32.sp,
                letterSpacing = 0.sp
            ),
            titleLarge = TextStyle(
                fontFamily = OpenSans,
                fontWeight = FontWeight.Medium, // Medium or SemiBold for titles
                fontSize = 22.sp,
                lineHeight = 28.sp,
                letterSpacing = 0.sp
            ),
            titleMedium = TextStyle(
                fontFamily = OpenSans,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.15.sp
            ),
            titleSmall = TextStyle(
                fontFamily = OpenSans,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.1.sp
            ),
            bodyLarge = TextStyle(
                fontFamily = OpenSans,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp
            ),
            bodyMedium = TextStyle(
                fontFamily = OpenSans,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.25.sp
            ),
            bodySmall = TextStyle(
                fontFamily = OpenSans,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.4.sp
            ),
            labelLarge = TextStyle(
                fontFamily = OpenSans,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.1.sp
            ),
            labelMedium = TextStyle(
                fontFamily = OpenSans,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.5.sp
            ),
            labelSmall = TextStyle(
                fontFamily = OpenSans,
                fontWeight = FontWeight.Medium,
                fontSize = 11.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.5.sp
            )
        )
    }

val WalleriaLogoTextStyle: TextStyle
    @Composable get() {
        return TextStyle(
            fontFamily = Pacifico,
            fontSize = 48.sp
        )
    }