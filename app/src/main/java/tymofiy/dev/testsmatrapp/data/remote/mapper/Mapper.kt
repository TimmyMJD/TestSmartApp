package tymofiy.dev.testsmatrapp.data.remote.mapper

import tymofiy.dev.testsmatrapp.data.local.model.GistEntity
import tymofiy.dev.testsmatrapp.data.remote.model.ApiGistModel

fun ApiGistModel.toEntity() =
    GistEntity(
        id = 0,
        userStatus = this.user_status
    )
