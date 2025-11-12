package com.andrii_a.walleria.ui.util

import com.andrii_a.walleria.domain.AppTheme
import com.andrii_a.walleria.domain.PhotoListDisplayOrder
import com.andrii_a.walleria.domain.PhotoQuality
import com.andrii_a.walleria.domain.SearchResultsContentFilter
import com.andrii_a.walleria.domain.SearchResultsDisplayOrder
import com.andrii_a.walleria.domain.SearchResultsPhotoColor
import com.andrii_a.walleria.domain.SearchResultsPhotoOrientation
import com.andrii_a.walleria.domain.TopicPhotosOrientation
import com.andrii_a.walleria.domain.TopicStatus
import com.andrii_a.walleria.domain.TopicsDisplayOrder
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import walleriamultiplatform.composeapp.generated.resources.Res
import walleriamultiplatform.composeapp.generated.resources.content_filter_high
import walleriamultiplatform.composeapp.generated.resources.content_filter_low
import walleriamultiplatform.composeapp.generated.resources.filter_color_any
import walleriamultiplatform.composeapp.generated.resources.filter_color_black
import walleriamultiplatform.composeapp.generated.resources.filter_color_black_white
import walleriamultiplatform.composeapp.generated.resources.filter_color_blue
import walleriamultiplatform.composeapp.generated.resources.filter_color_green
import walleriamultiplatform.composeapp.generated.resources.filter_color_magenta
import walleriamultiplatform.composeapp.generated.resources.filter_color_orange
import walleriamultiplatform.composeapp.generated.resources.filter_color_purple
import walleriamultiplatform.composeapp.generated.resources.filter_color_red
import walleriamultiplatform.composeapp.generated.resources.filter_color_teal
import walleriamultiplatform.composeapp.generated.resources.filter_color_white
import walleriamultiplatform.composeapp.generated.resources.filter_color_yellow
import walleriamultiplatform.composeapp.generated.resources.filter_order_latest
import walleriamultiplatform.composeapp.generated.resources.filter_order_relevance
import walleriamultiplatform.composeapp.generated.resources.ic_landscape_outlined
import walleriamultiplatform.composeapp.generated.resources.ic_portrait_outlined
import walleriamultiplatform.composeapp.generated.resources.ic_square_outlined
import walleriamultiplatform.composeapp.generated.resources.order_featured
import walleriamultiplatform.composeapp.generated.resources.order_latest
import walleriamultiplatform.composeapp.generated.resources.order_oldest
import walleriamultiplatform.composeapp.generated.resources.order_popular
import walleriamultiplatform.composeapp.generated.resources.order_position
import walleriamultiplatform.composeapp.generated.resources.orientation_any
import walleriamultiplatform.composeapp.generated.resources.orientation_landscape
import walleriamultiplatform.composeapp.generated.resources.orientation_portrait
import walleriamultiplatform.composeapp.generated.resources.orientation_squarish
import walleriamultiplatform.composeapp.generated.resources.photo_quality_high
import walleriamultiplatform.composeapp.generated.resources.photo_quality_low
import walleriamultiplatform.composeapp.generated.resources.photo_quality_medium
import walleriamultiplatform.composeapp.generated.resources.photo_quality_raw
import walleriamultiplatform.composeapp.generated.resources.photo_quality_thumbnail
import walleriamultiplatform.composeapp.generated.resources.theme_dark
import walleriamultiplatform.composeapp.generated.resources.theme_light
import walleriamultiplatform.composeapp.generated.resources.theme_system_default
import walleriamultiplatform.composeapp.generated.resources.topic_status_closed
import walleriamultiplatform.composeapp.generated.resources.topic_status_open
import walleriamultiplatform.composeapp.generated.resources.topic_status_other

val PhotoListDisplayOrder.titleRes: StringResource
    get() = when (this) {
        PhotoListDisplayOrder.LATEST -> Res.string.order_latest
        PhotoListDisplayOrder.OLDEST -> Res.string.order_oldest
        PhotoListDisplayOrder.POPULAR -> Res.string.order_popular
    }

val SearchResultsDisplayOrder.titleRes: StringResource
    get() = when (this) {
        SearchResultsDisplayOrder.RELEVANT -> Res.string.filter_order_relevance
        SearchResultsDisplayOrder.LATEST -> Res.string.filter_order_latest
    }

val SearchResultsContentFilter.titleRes: StringResource
    get() = when (this) {
        SearchResultsContentFilter.LOW -> Res.string.content_filter_low
        SearchResultsContentFilter.HIGH -> Res.string.content_filter_high
    }

val SearchResultsPhotoColor.titleRes: StringResource
    get() = when (this) {
        SearchResultsPhotoColor.ANY -> Res.string.filter_color_any
        SearchResultsPhotoColor.BLACK_AND_WHITE -> Res.string.filter_color_black_white
        SearchResultsPhotoColor.BLACK -> Res.string.filter_color_black
        SearchResultsPhotoColor.WHITE -> Res.string.filter_color_white
        SearchResultsPhotoColor.YELLOW -> Res.string.filter_color_yellow
        SearchResultsPhotoColor.ORANGE -> Res.string.filter_color_orange
        SearchResultsPhotoColor.RED -> Res.string.filter_color_red
        SearchResultsPhotoColor.PURPLE -> Res.string.filter_color_purple
        SearchResultsPhotoColor.MAGENTA -> Res.string.filter_color_magenta
        SearchResultsPhotoColor.GREEN -> Res.string.filter_color_green
        SearchResultsPhotoColor.TEAL -> Res.string.filter_color_teal
        SearchResultsPhotoColor.BLUE -> Res.string.filter_color_blue
    }

val SearchResultsPhotoOrientation.titleRes: StringResource
    get() = when (this) {
        SearchResultsPhotoOrientation.ANY -> Res.string.orientation_any
        SearchResultsPhotoOrientation.LANDSCAPE -> Res.string.orientation_landscape
        SearchResultsPhotoOrientation.PORTRAIT -> Res.string.orientation_portrait
        SearchResultsPhotoOrientation.SQUARISH -> Res.string.orientation_squarish
    }

val SearchResultsPhotoOrientation.iconRes: DrawableResource
    get() = when (this) {
        SearchResultsPhotoOrientation.ANY -> Res.drawable.ic_landscape_outlined // TODO: specify a default value
        SearchResultsPhotoOrientation.LANDSCAPE -> Res.drawable.ic_landscape_outlined
        SearchResultsPhotoOrientation.PORTRAIT -> Res.drawable.ic_portrait_outlined
        SearchResultsPhotoOrientation.SQUARISH -> Res.drawable.ic_square_outlined
    }

val TopicPhotosOrientation.titleRes: StringResource
    get() = when (this) {
        TopicPhotosOrientation.LANDSCAPE -> Res.string.orientation_landscape
        TopicPhotosOrientation.PORTRAIT -> Res.string.orientation_portrait
        TopicPhotosOrientation.SQUARISH -> Res.string.orientation_squarish
    }

val TopicPhotosOrientation.iconRes: DrawableResource
    get() = when (this) {
        TopicPhotosOrientation.LANDSCAPE -> Res.drawable.ic_landscape_outlined
        TopicPhotosOrientation.PORTRAIT -> Res.drawable.ic_portrait_outlined
        TopicPhotosOrientation.SQUARISH -> Res.drawable.ic_square_outlined
    }

val TopicsDisplayOrder.titleRes: StringResource
    get() = when (this) {
        TopicsDisplayOrder.FEATURED -> Res.string.order_featured
        TopicsDisplayOrder.LATEST -> Res.string.order_latest
        TopicsDisplayOrder.OLDEST -> Res.string.order_oldest
        TopicsDisplayOrder.POSITION -> Res.string.order_position
    }

val TopicStatus.titleRes: StringResource
    get() = when (this) {
        TopicStatus.OPEN -> Res.string.topic_status_open
        TopicStatus.CLOSED -> Res.string.topic_status_closed
        TopicStatus.OTHER -> Res.string.topic_status_other
    }

val PhotoQuality.titleRes: StringResource
    get() = when (this) {
        PhotoQuality.RAW -> Res.string.photo_quality_raw
        PhotoQuality.HIGH -> Res.string.photo_quality_high
        PhotoQuality.MEDIUM -> Res.string.photo_quality_medium
        PhotoQuality.LOW -> Res.string.photo_quality_low
        PhotoQuality.THUMBNAIL -> Res.string.photo_quality_thumbnail
    }

val AppTheme.titleRes: StringResource
    get() = when (this) {
        AppTheme.SYSTEM_DEFAULT -> Res.string.theme_system_default
        AppTheme.LIGHT -> Res.string.theme_light
        AppTheme.DARK -> Res.string.theme_dark
    }
