package tymofiy.dev.testsmatrapp.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tymofiy.dev.testsmatrapp.R
import tymofiy.dev.testsmatrapp.ui.fragments.WebViewFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val fragment =
            this.supportFragmentManager.primaryNavigationFragment?.childFragmentManager?.fragments?.first()
        if (fragment is WebViewFragment) {
            fragment.activityResult(requestCode, resultCode, data)
        }
    }
}