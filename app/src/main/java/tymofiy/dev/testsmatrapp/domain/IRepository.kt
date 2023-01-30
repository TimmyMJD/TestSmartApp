package tymofiy.dev.testsmatrapp.domain

interface IRepository {
    suspend fun loadGist()
}