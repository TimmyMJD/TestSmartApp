package tymofiy.dev.testsmatrapp.ui.fragments.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import tymofiy.dev.testsmatrapp.R
import tymofiy.dev.testsmatrapp.databinding.FragmentGameBinding
import tymofiy.dev.testsmatrapp.utils.SharedPreferencesUtils
import kotlin.random.Random

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private var blackIsSelected: Boolean = false
    private var redIsSelected: Boolean = false

    private val sectors = arrayOf(
        "black", "red", "black", "red", "black",
        "red", "black", "red", "black",
        "red", "black", "red"
    )

    private var degree = 0
    private var degreeOld = 0
    private val halfSector = 360f / 12f / 2f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentGameBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.scoreTextView.text =
            SharedPreferencesUtils(requireContext()).getUserWin().toString()

        binding.buttonBackToMenu.setOnClickListener {
            findNavController()
                .navigate(R.id.action_gameFragment_to_gameMenuFragment)
        }

        binding.buttonRed.setOnClickListener {
            redIsSelected = true
            blackIsSelected = false
            Toast.makeText(requireContext(), "Red is selected", Toast.LENGTH_SHORT).show()
        }

        binding.buttonBlack.setOnClickListener {
            blackIsSelected = true
            redIsSelected = false
            Toast.makeText(requireContext(), "Black is selected", Toast.LENGTH_SHORT).show()
        }

        binding.buttonSpin.setOnClickListener {
            degreeOld = degree % 360
            degree = Random.nextInt(360) + 720

            val rotateAnim = RotateAnimation(
                degreeOld.toFloat(), degree.toFloat(),
                RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f
            )
            rotateAnim.duration = 3600
            rotateAnim.fillAfter = true
            rotateAnim.interpolator = DecelerateInterpolator()
            rotateAnim.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}
                override fun onAnimationEnd(animation: Animation) {
                    val sector = getSector(360 - degree % 360)
                    if (sector == "black" && blackIsSelected) {
                        val win = SharedPreferencesUtils(requireContext()).getUserWin()
                        SharedPreferencesUtils(requireContext()).saveUserWin(win + 10)
                        binding.scoreTextView.text =
                            SharedPreferencesUtils(requireContext()).getUserWin().toString()
                    } else if (sector == "red" && redIsSelected) {
                        val win = SharedPreferencesUtils(requireContext()).getUserWin()
                        SharedPreferencesUtils(requireContext()).saveUserWin(win + 10)
                        binding.scoreTextView.text =
                            SharedPreferencesUtils(requireContext()).getUserWin().toString()
                    } else if (!blackIsSelected && !redIsSelected) {
                        Toast.makeText(requireContext(), "Select black or red!", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onAnimationRepeat(animation: Animation) {}
            })
            binding.wheel.startAnimation(rotateAnim)
        }
    }

    private fun getSector(degrees: Int): String? {
        var i = 0
        var text: String? = null
        do {
            val start = halfSector * (i * 2 + 1)
            val end = halfSector * (i * 2 + 3)
            if (degrees >= start && degrees < end) {
                text = sectors[i]
            }
            i++
        } while (text == null && i < sectors.size)
        return text
    }
}