package tymofiy.dev.testsmatrapp.ui.fragments.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import tymofiy.dev.testsmatrapp.R
import tymofiy.dev.testsmatrapp.databinding.FragmentGameMenuBinding
import tymofiy.dev.testsmatrapp.utils.SharedPreferencesUtils

class GameMenuFragment : Fragment() {

    private lateinit var binding: FragmentGameMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentGameMenuBinding.inflate(layoutInflater)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonExit.setOnClickListener {
            activity?.moveTaskToBack(true)
            activity?.finish()
        }

        binding.buttonHowToPlay.setOnClickListener {
            val newFragment = HowToPlayDialogFragment()
            this.fragmentManager?.let { it1 -> newFragment.show(it1, "game") }
        }

        binding.buttonStartGame.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_gameMenuFragment_to_gameFragment)
        }
    }
}