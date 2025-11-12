package com.andrii_a.walleria.ui.util

import com.andrii_a.walleria.domain.models.photo.PhotoExif

fun PhotoExif.formCameraNameOrEmpty(): String {
    val makeList = this.make?.split(" ")?.map { it.trim() }
    val modelList = this.model?.split(" ")?.map { it.trim() }
    return if (makeList?.map { it.lowercase() }
            ?.intersect((modelList?.map { it.lowercase() } ?: emptyList()).toSet())
            ?.isEmpty() == true
    ) {
        "${makeList.first()} $model"
    } else {
        model.orEmpty()
    }
}
