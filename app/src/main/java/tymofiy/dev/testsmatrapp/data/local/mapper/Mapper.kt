package tymofiy.dev.testsmatrapp.data.local.mapper

import tymofiy.dev.testsmatrapp.data.local.model.GistEntity
import tymofiy.dev.testsmatrapp.domain.model.DataGistModel

fun GistEntity.toDomain() =
    DataGistModel(
        userStatus = this.userStatus
    )