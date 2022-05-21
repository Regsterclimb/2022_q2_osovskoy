package com.example.a2022_q2_osovskoy.ui.second_screen

import android.animation.AnimatorSet
import android.os.Bundle
import android.view.View
import androidx.core.animation.doOnEnd
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.FragmentSecondBinding
import com.example.a2022_q2_osovskoy.presentation.MultiViewModelFactory
import com.example.a2022_q2_osovskoy.presentation.second_screen.SecondScreenModelState
import com.example.a2022_q2_osovskoy.presentation.second_screen.SecondScreenViewModel
import com.example.a2022_q2_osovskoy.ui.extentions.fadeIn
import com.example.a2022_q2_osovskoy.ui.extentions.fadeOut
import com.example.a2022_q2_osovskoy.ui.extentions.shake
import com.example.a2022_q2_osovskoy.ui.extentions.toDp
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SecondFragment : DaggerFragment(R.layout.fragment_second) {

    private val viewBinding by viewBinding(FragmentSecondBinding::bind)

    @Inject
    lateinit var multiViewModelFactory: MultiViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, multiViewModelFactory)[SecondScreenViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            observeScreenState(this)

            acceptButtonSecondScreen.setOnClickListener {
                viewModel.renderViewState(SecondScreenViewState
                    .ClickOnAcceptButton(secondScreenMainEditText.text.toString())
                )
            }
        }
    }

    private fun observeScreenState(binding: FragmentSecondBinding) {
        viewModel.state.observe(viewLifecycleOwner) { screenState ->
            when (screenState) {
                SecondScreenModelState.Success -> startEndingAnimation(getEndingAnimator(binding))

                SecondScreenModelState.WrongTextInput -> shakeMyTextBox(binding)

                SecondScreenModelState.ExitFromScreen -> parentFragmentManager.popBackStack()

                else -> throw IllegalArgumentException()
            }
        }
    }

    private fun getEndingAnimator(binding: FragmentSecondBinding): AnimatorSet {
        val animationSet = AnimatorSet()
        with(binding) {
            animationSet
                .play(secondScreenContainer.fadeOut(HIDE_DURATION, HIDE_END_VALUE))
                .before(secondScreenProgressBar.fadeIn(SHOW_DURATION, SHOW_START_VALUE,
                    SHOW_END_VALUE))
        }
        return animationSet
    }

    private fun startEndingAnimation(animatorSet: AnimatorSet) {
        with(animatorSet) {
            start()
            doOnEnd {
                viewModel.renderViewState(SecondScreenViewState.ExitFromScreen)
            }
        }
    }

    private fun shakeMyTextBox(binding: FragmentSecondBinding) {
        val leftValue = SHAKE_LEFT_VALUE.toDp()
        val rightValue = SHAKE_RIGHT_VALUE.toDp()
        val defaultValue = SHAKE_DEFAULT_VALUE
        val shakeDuration = SHAKE_DURATION
        with(binding) {
            secondScreenMainEditText.shake(shakeDuration, leftValue, rightValue, defaultValue)
                .start()
            secondScreenMainTextBox.shake(shakeDuration, leftValue, rightValue, defaultValue)
                .start()
        }
    }

    companion object {
        const val HIDE_END_VALUE = 0f
        const val SHOW_START_VALUE = 0.5f
        const val SHOW_END_VALUE = 1f
        const val HIDE_DURATION = 200L
        const val SHOW_DURATION = 1000L
        const val SHAKE_LEFT_VALUE = -5f
        const val SHAKE_RIGHT_VALUE = 5f
        const val SHAKE_DEFAULT_VALUE = 0F
        const val SHAKE_DURATION = 400L
    }
}