package tymofiy.dev.testsmatrapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class ApiGistModel(
    @SerializedName("user_status")
    val user_status: Boolean
)