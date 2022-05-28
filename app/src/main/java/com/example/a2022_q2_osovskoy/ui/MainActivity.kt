package com.example.a2022_q2_osovskoy.ui

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.ActivityMainBinding
import com.example.a2022_q2_osovskoy.presentation.FileLoadingProgressState
import com.example.a2022_q2_osovskoy.presentation.MainActivityModelState
import com.example.a2022_q2_osovskoy.presentation.MultiViewModelFactory
import com.example.a2022_q2_osovskoy.presentation.UploadScreenViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    private val viewBinding by viewBinding(ActivityMainBinding::bind)

    @Inject
    lateinit var multiViewModelFactory: MultiViewModelFactory

    private val viewModel: UploadScreenViewModel by lazy {
        ViewModelProvider(this, multiViewModelFactory)[UploadScreenViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(viewBinding) {
            observeProgress(this)
            setUpListeners(this)
            observeModelState(this)
        }
    }

    private fun setUpListeners(binding: ActivityMainBinding) {
        binding.uploadButton.setOnClickListener {
            viewModel.renderViewEvent(MainActivityViewEvent.ClickOnUploadFileButton)
        }
    }

    private fun observeProgress(binding: ActivityMainBinding) {
        viewModel.uploadProgress.observe(this) { fileLoadingState ->
            when (fileLoadingState) {
                is FileLoadingProgressState.Loading -> {
                    binding.uploadProgressBar.progress = fileLoadingState.value
                }
                FileLoadingProgressState.Error -> showToast(R.string.fileUploadError)

                FileLoadingProgressState.Success -> showToast(R.string.fileSuccessUpload)

                else -> throw IllegalArgumentException()
            }
        }
    }

    private fun observeModelState(binding: ActivityMainBinding) {
        viewModel.mainActivityState.observe(this) { state ->
            when (state) {
                is MainActivityModelState.FileLoadingSuccess -> {
                    binding.fileInfo.text = state.filePostInfo.toString()
                    showLoadingSuccess(binding)
                }
                MainActivityModelState.FileLoading -> showLoading(binding)

                MainActivityModelState.FileLoadingErrorIO -> showLoadingErrorIo(binding)

                MainActivityModelState.FileLoadingErrorHttp -> showLoadingErrorHttp(binding)

                else -> throw IllegalArgumentException()
            }
        }
    }

    private fun showLoadingSuccess(binding: ActivityMainBinding) {
        with(binding) {
            uploadButton.isEnabled = true
            uploadProgressBar.isVisible = false
            fileInfo.isVisible = true
        }
    }

    private fun showLoading(binding: ActivityMainBinding) {
        with(binding) {
            uploadProgressBar.progress = DEFAULT_BAR_PROGRESS
            uploadProgressBar.isVisible = true
            uploadButton.isEnabled = false
            fileInfo.isVisible = false
        }
    }

    private fun showLoadingErrorIo(binding: ActivityMainBinding) {
        with(binding) {
            uploadProgressBar.isVisible = false
            uploadButton.isEnabled = true
            fileInfo.isVisible = false
            showToast(R.string.ioError)
        }
    }

    private fun showLoadingErrorHttp(binding: ActivityMainBinding) {
        with(binding) {
            uploadProgressBar.isVisible = false
            uploadButton.isEnabled = true
            fileInfo.isVisible = false
            showToast(R.string.httpError)
        }
    }

    private fun showToast(@StringRes res: Int) {
        Toast.makeText(this, res, Toast.LENGTH_SHORT)
            .show()
    }

    companion object {
        const val DEFAULT_BAR_PROGRESS = 0
    }
}