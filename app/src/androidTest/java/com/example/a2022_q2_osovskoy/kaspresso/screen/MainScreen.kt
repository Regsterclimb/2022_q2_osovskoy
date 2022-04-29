package com.example.a2022_q2_osovskoy.kaspresso.screen

import android.view.View
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.example.a2022_q2_osovskoy.R
import org.hamcrest.Matcher


object MainScreen : Screen<MainScreen>() {

    val currenciesList = KRecyclerView(
        builder = { withId(R.id.currenciesRecycler) },
        itemTypeBuilder = { itemType(::Currency) }
    )

    class Currency(parent: Matcher<View>) : KRecyclerItem<Currency>(parent) {

        val currencyName = KTextView(parent) { withId(R.id.holderCurrencyName) }
        val currencyValue = KTextView(parent) { withId(R.id.holderValue) }
    }
}