package com.andrii_a.walleria.data.remote.services

import com.andrii_a.walleria.domain.PhotoQuality
import com.andrii_a.walleria.domain.models.photo.Photo
import com.andrii_a.walleria.domain.services.DownloadId
import com.andrii_a.walleria.domain.services.PhotoDownloader
import com.andrii_a.walleria.ui.util.downloadFilename
import com.andrii_a.walleria.ui.util.getUrlByQuality
import io.github.vinceglb.filekit.FileKit
import io.github.vinceglb.filekit.dialogs.openDirectoryPicker
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readRawBytes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import multiplatform.network.cmptoast.ToastDuration
import multiplatform.network.cmptoast.showToast
import java.io.File

class DesktopPhotoDownloader : PhotoDownloader {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun downloadPhoto(
        photo: Photo,
        quality: PhotoQuality
    ): DownloadId {
        val client = HttpClient()

        coroutineScope.launch {
            val directory = FileKit.openDirectoryPicker() ?: return@launch
            val outputFile = File(directory.file, photo.downloadFilename)

            try {
                val response: HttpResponse = client.get(photo.getUrlByQuality(quality))
                val bytes = response.readRawBytes()

                outputFile.writeBytes(bytes)
                showToast(
                    message = "File saved to ${outputFile.absolutePath}",
                    duration = ToastDuration.Long
                )
            } finally {
                client.close()
            }
        }

        return 0
    }
}