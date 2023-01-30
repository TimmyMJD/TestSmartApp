package tymofiy.dev.testsmatrapp.ui.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import tymofiy.dev.testsmatrapp.base.MainApplication
import tymofiy.dev.testsmatrapp.databinding.FragmentWebViewBinding
import tymofiy.dev.testsmatrapp.ui.view_model.ViewModelFactory
import tymofiy.dev.testsmatrapp.ui.view_model.WebViewViewModel
import tymofiy.dev.testsmatrapp.utils.SharedPreferencesUtils

class WebViewFragment : Fragment() {

    private lateinit var binding: FragmentWebViewBinding
    private lateinit var valCallback: ValueCallback<Array<Uri>>

    private val webViewViewModel: WebViewViewModel by viewModels {
        (requireActivity().application as MainApplication).run {
            ViewModelFactory(repository, shStorage)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentWebViewBinding.inflate(layoutInflater)
    }

    private var onBackClickHandler: Handler? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.webView.settingsInit(true)
        binding.webView.webViewClient = WebViewClient()
        CookieManager.getInstance().setAcceptThirdPartyCookies(binding.webView, true)
        CookieManager.getInstance().acceptCookie()
        webViewViewModel.baseUrlMutableLiveData.observe(viewLifecycleOwner) { url ->
            url ?: return@observe
            binding.webView.loadUrl(url)
        }
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (onBackClickHandler != null) {
                        activity?.finish()
                        return
                    }
                    if (binding.webView.canGoBack()) {
                        binding.webView.goBack()
                        super.setEnabled(true)
                    } else {
                        Toast.makeText(
                            context, "Press once again to exit!", Toast.LENGTH_SHORT
                        ).show()
                        onBackClickHandler = Handler(Looper.getMainLooper())
                        onBackClickHandler?.postDelayed({
                            onBackClickHandler = null
                        }, 2000)
                    }
                }
            })

        binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                if (newProgress == 100) {
                    val progressBar = binding.progressBar
                    progressBar.visibility = View.GONE
                }
            }
        }
        return binding.root
    }

    fun activityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 222) {
            val finalData =
                if (data == null || resultCode != Activity.RESULT_OK) {
                    null
                } else data.data

            if (finalData != null) {
                valCallback.onReceiveValue(arrayOf(finalData))
            }
        }
    }
}