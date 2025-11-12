package com.andrii_a.walleria.ui.util

import androidx.compose.runtime.Composable
import com.andrii_a.walleria.domain.PhotoQuality
import com.andrii_a.walleria.domain.models.photo.Photo
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.format.char
import org.jetbrains.compose.resources.stringResource
import walleriamultiplatform.composeapp.generated.resources.Res
import walleriamultiplatform.composeapp.generated.resources.user_full_name_formatted
import kotlin.time.ExperimentalTime

fun Photo.getUrlByQuality(quality: PhotoQuality = PhotoQuality.HIGH): String =
    when (quality) {
        PhotoQuality.RAW -> this.urls.raw
        PhotoQuality.HIGH -> this.urls.full
        PhotoQuality.MEDIUM -> this.urls.regular
        PhotoQuality.LOW -> this.urls.small
        PhotoQuality.THUMBNAIL -> this.urls.thumb
    }

val Photo.userFullName: String
    @Composable
    get() = stringResource(
        Res.string.user_full_name_formatted,
        this.user?.firstName.orEmpty(),
        this.user?.lastName.orEmpty()
    )

val Photo.userNickname: String
    get() = this.user?.username.orEmpty()

/*val Photo.primaryColorInt: Int
    get() = this.color.toColorInt()*/

val Photo.downloadFilename: String
    get() = "${this.id}_${this.userNickname}_unsplash.jpg"

val Photo.downloadFilename2: String
    get() {
        val name = this.urls.full.substringAfterLast("/").substringBefore("?")
        val extension = this.urls.full.substringAfter("fm=").substringBefore("&")

        return "$name.$extension"
    }

@OptIn(ExperimentalTime::class)
val Photo.createdDateTime: String
    get() {
        @OptIn(FormatStringsInDatetimeFormats::class)
        val dateTimeFormat = LocalDateTime.Format {
            byUnicodePattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        }

        val date = dateTimeFormat.parse(this.createdAt)

        val outputFormat = LocalDateTime.Format {
            dayOfWeek(DayOfWeekNames.ENGLISH_ABBREVIATED)
            chars(", ")
            monthName(MonthNames.ENGLISH_ABBREVIATED)
            char(' ')
            year()
            chars(" \u2022 ")
            hour()
            char(':')
            minute()
        }

        return outputFormat.format(date)
    }