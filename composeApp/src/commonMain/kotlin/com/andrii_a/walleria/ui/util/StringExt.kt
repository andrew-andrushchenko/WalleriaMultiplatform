package com.andrii_a.walleria.ui.util

import androidx.compose.runtime.Composable
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toInstant
import org.jetbrains.compose.resources.stringResource
import walleriamultiplatform.composeapp.generated.resources.Res
import walleriamultiplatform.composeapp.generated.resources.days_ago_formatted
import walleriamultiplatform.composeapp.generated.resources.hour_ago
import walleriamultiplatform.composeapp.generated.resources.hours_ago_formatted
import walleriamultiplatform.composeapp.generated.resources.just_now
import walleriamultiplatform.composeapp.generated.resources.minute_ago
import walleriamultiplatform.composeapp.generated.resources.minutes_ago_formatted
import walleriamultiplatform.composeapp.generated.resources.yesterday
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

private const val SECOND_MILLIS = 1000
private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
private const val DAY_MILLIS = 24 * HOUR_MILLIS

@OptIn(ExperimentalTime::class)
val String?.timeAgoLocalizedString: String?
    @Composable
    get() {
        @OptIn(FormatStringsInDatetimeFormats::class)
        val dateTimeFormat = LocalDateTime.Format {
            byUnicodePattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        }

        val date = dateTimeFormat.parse(this.orEmpty())
        var time = date.toInstant(timeZone = TimeZone.UTC).toEpochMilliseconds()

        if (time < 1000000000000L) {
            time *= 1000
        }

        val now = Clock.System.now().toEpochMilliseconds()
        if (time !in 1..now) {
            return null
        }

        val diff = now - time

        return when {
            diff < MINUTE_MILLIS -> stringResource(Res.string.just_now)
            diff < 2 * MINUTE_MILLIS -> stringResource(Res.string.minute_ago)
            diff < 50 * MINUTE_MILLIS -> stringResource(
                Res.string.minutes_ago_formatted,
                diff / MINUTE_MILLIS
            )

            diff < 90 * MINUTE_MILLIS -> stringResource(Res.string.hour_ago)
            diff < 24 * HOUR_MILLIS -> stringResource(
                Res.string.hours_ago_formatted,
                diff / HOUR_MILLIS
            )

            diff < 48 * HOUR_MILLIS -> stringResource(Res.string.yesterday)
            else -> stringResource(Res.string.days_ago_formatted, diff / DAY_MILLIS)
        }
    }