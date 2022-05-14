package com.example.a2022_q2_osovskoy.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.ActivityMainBinding
import com.example.a2022_q2_osovskoy.domain.entity.ViewColorState
import com.example.a2022_q2_osovskoy.domain.entity.ViewEvent
import com.example.a2022_q2_osovskoy.domain.entity.ViewState
import kotlinx.coroutines.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private var viewModel: MainViewModel by Delegates.notNull()

    private val viewBinding by viewBinding(ActivityMainBinding::bind)

    private var coroutineScope: CoroutineScope by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coroutineScope = CoroutineScope(Dispatchers.IO)
        viewModel = MainViewModel((this.applicationContext as App).personUseCase)

        with(viewBinding) {
            observeScreen(this)
            observeColor(this)
            setUpAdapter(this)
            observeSearch(this)
        }
    }

    private fun observeSearch(binding: ActivityMainBinding) =
        binding.editTextName.afterTextChanged { text ->
            coroutineScope.launch {
                delay(SEARCH_DELAY)
                viewModel.obtainEvent(ViewEvent.Typing(text))
            }
        }

    private fun observeColor(binding: ActivityMainBinding) =
        viewModel.colorState.observe(this) { state ->
            when (state) {
                is ViewColorState.Success -> binding.mainScreen.setBackgroundColor(state.color)
                is ViewColorState.Error -> showToast(R.string.error)
            }
        }

    private fun observeScreen(binding: ActivityMainBinding) =
        viewModel.screenState.observe(this@MainActivity) { state ->
            val adapter = binding.personsRecycler.adapter as PersonAdapter
            when (state) {
                is ViewState.Success -> adapter.submitList(state.list)
                is ViewState.Empty -> adapter.submitList(emptyList())
                is ViewState.Error -> showToast(R.string.error)
                is ViewState.Loading -> {}
            }
        }

    private fun setUpAdapter(binding: ActivityMainBinding) {
        binding.personsRecycler.adapter = PersonAdapter(
            onMenuClick = {
                viewModel.obtainEvent(ViewEvent.Click)
            }
        )
    }

    private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) =
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        }
        )

    private fun showToast(@StringRes value: Int) =
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show()

    override fun onDestroy() {
        coroutineScope.cancel()
        super.onDestroy()
    }

    companion object {
        const val SEARCH_DELAY = 1000L
    }
}