package tymofiy.dev.testsmatrapp.utils

import android.content.Context

class SharedPreferencesUtils(context: Context) {

    private val data = context
        .getSharedPreferences("Data", Context.MODE_PRIVATE)!!

    fun saveUserState(state: Boolean) {
        data.edit().apply {
            putString("state", state.toString())
        }.apply()
    }

    fun getUserState(): Boolean? = data.getString("state", null)?.toBooleanStrictOrNull()

    fun saveUserWin(win: Int) {
        data.edit().apply {
            putInt("win", win)
        }.apply()
    }

    fun getUserWin(): Int = data.getInt("win", 0)
}