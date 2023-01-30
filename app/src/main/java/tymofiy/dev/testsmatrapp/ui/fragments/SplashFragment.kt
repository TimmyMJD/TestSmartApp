package tymofiy.dev.testsmatrapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import tymofiy.dev.testsmatrapp.R
import tymofiy.dev.testsmatrapp.base.MainApplication
import tymofiy.dev.testsmatrapp.ui.view_model.SplashViewModel
import tymofiy.dev.testsmatrapp.ui.view_model.ViewModelFactory

class SplashFragment : Fragment() {

    private val splashViewModel: SplashViewModel by viewModels {
        (requireActivity().application as MainApplication).run {
            ViewModelFactory(repository, shStorage)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        splashViewModel.getUserStatus()
        splashViewModel.userStatus.observe(viewLifecycleOwner) {
            if (!it ) {
                NavHostFragment.findNavController(this)
                    .navigate(R.id.action_splashFragment_to_gameMenuFragment)
            } else {
                NavHostFragment.findNavController(this)
                    .navigate(R.id.action_splashFragment_to_webViewFragment)
            }
        }
    }
}