package com.example.a2022_q2_osovskoy.ui.second_screen

import android.animation.AnimatorSet
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.FragmentSecondBinding
import com.example.a2022_q2_osovskoy.domain.entity.SecondScreenModelState
import com.example.a2022_q2_osovskoy.domain.entity.SecondScreenViewState
import com.example.a2022_q2_osovskoy.presentation.MultiViewModelFactory
import com.example.a2022_q2_osovskoy.presentation.second_screen.SecondScreenViewModel
import com.example.a2022_q2_osovskoy.ui.extentions.fadeIn
import com.example.a2022_q2_osovskoy.ui.extentions.fadeOut
import com.example.a2022_q2_osovskoy.ui.extentions.shake
import com.example.a2022_q2_osovskoy.ui.extentions.toDp
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class FragmentSecond : Fragment(R.layout.fragment_second) {

    private val viewBinding by viewBinding(FragmentSecondBinding::bind)

    @Inject
    lateinit var multiViewModelFactory: MultiViewModelFactory

    lateinit var viewModel: SecondScreenViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProvider(this,multiViewModelFactory)[SecondScreenViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            acceptButtonSecondScreen.setOnClickListener {
                viewModel.renderViewState(SecondScreenViewState
                    .ClickOnAcceptButton(secondScreenMainEditText.text.toString())
                )
            }
        }
        observeScreenState()
    }

    private fun observeScreenState() {
        viewModel.state.observe(viewLifecycleOwner) { screenState ->
            when (screenState) {
                SecondScreenModelState.Success -> {
                    setUpAnimations()
                }
                SecondScreenModelState.WrongTextInput -> {
                    shakeMyTextBox()
                }
                is SecondScreenModelState.ExitFromScreen -> {
                    parentFragmentManager.popBackStack()
                }
            }
        }
    }

    //todo()
    private fun setUpAnimations() {
        val animationSet = AnimatorSet()
        with(viewBinding) {
            animationSet
                .play(secondScreenContainer.fadeOut(HIDE_DURATION, HIDE_END_VALUE))
                .before(secondScreenProgressBar.fadeIn(SHOW_DURATION, SHOW_START_VALUE,
                    SHOW_END_VALUE))
            animationSet.start()
            animationSet.doOnEnd {
                viewModel.renderViewState(SecondScreenViewState.ExitFromScreen)
            }
        }
    }

    private fun shakeMyTextBox() {
        with(viewBinding) {
            secondScreenMainEditText.shake(SHAKE_LEFT_VALUE.toDp(), SHAKE_RIGHT_VALUE.toDp(), SHAKE_DEFAULT_VALUE)
                .start()
            secondScreenMainTextBox.shake(SHAKE_LEFT_VALUE.toDp(), SHAKE_RIGHT_VALUE.toDp(), SHAKE_DEFAULT_VALUE)
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
    }
}