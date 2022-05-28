package com.example.a2022_q2_osovskoy.data.repository

import com.example.a2022_q2_osovskoy.data.data_source.file.FileDataSource
import com.example.a2022_q2_osovskoy.data.data_source.remote.UploadFileDataSource
import com.example.a2022_q2_osovskoy.data.data_source.remote.support.ProgressReport
import com.example.a2022_q2_osovskoy.data.model.ResultState
import com.example.a2022_q2_osovskoy.domain.entity.ProgressResult
import com.example.a2022_q2_osovskoy.domain.repository.FileRepository
import com.example.a2022_q2_osovskoy.extentions.toFilePostInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class FileRepositoryImpl @Inject constructor(
    private val fileDataSource: FileDataSource,
    private val uploadFileDataSource: UploadFileDataSource,
    private val dispatcherIo: CoroutineDispatcher = Dispatchers.IO,
    private val progressReport: ProgressReport,
) : FileRepository {

    override suspend fun uploadFile(): ResultState = withContext(dispatcherIo) {
        try {
            ResultState.Success(uploadFileDataSource.upload(fileDataSource.get()).toFilePostInfo())
        } catch (error: HttpException) {
            error.printStackTrace()
            ResultState.MainError.Http
        } catch (e: IOException) {
            e.printStackTrace()
            ResultState.MainError.IO
        }
    }

    override fun getProgress(): ProgressResult = progressReport.getReport()
}