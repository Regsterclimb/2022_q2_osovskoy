package com.example.a2022_q2_osovskoy.di

import com.example.a2022_q2_osovskoy.presentation.MyFragment
import dagger.Subcomponent

@Subcomponent
interface MyFragmentComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MyFragmentComponent
    }

    fun inject(fragment: MyFragment)
}
