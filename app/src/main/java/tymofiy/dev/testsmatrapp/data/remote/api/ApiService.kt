package tymofiy.dev.testsmatrapp.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url
import tymofiy.dev.testsmatrapp.data.remote.model.ApiGistModel

interface ApiService {
    @GET
    suspend fun getGistInfo(
        @Url url: String = "https://gist.githubusercontent.com/TimmyMJD/fa460b0c6321164252907b01c5370387/raw/TestSmartApp(tymofiy.dev.testsmatrapp)",
    ): Response<ApiGistModel>
}

