package com.example.a2022_q2_osovskoy

import io.reactivex.rxjava3.core.Observable

object MyObservableOperator {

    object Task1 {

        /**
         * Реализовать поток данных вида:
         * 10 , 9 , .. ,5, 4, 3, 2, 1
         *
         * Исключить последние 5 символов
         */

        fun solve(): Observable<Int> = Observable.fromIterable(10 downTo 1).skipLast(5)
    }
}