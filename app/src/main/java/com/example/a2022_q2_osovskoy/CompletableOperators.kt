package com.example.a2022_q2_osovskoy

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single

object CompletableOperators {

	object Task1 {

		/**
		 * Вернуть Completable, который моментально успешно завершает работу.
		 */
		fun solve(): Completable = Completable.complete()
	}

	object Task2 {

		/**
		 * Вернуть Completable, который моментально завершает работу с любой ошибкой.
		 */
		fun solve(): Completable = Completable.error(Throwable())
	}

	object Task3 {

		/**
		 * Преобразовать вызов блокирующей поток операции в Completable.
		 */
		fun solve(worker: Worker): Completable = Completable.fromAction { worker.doWork() }


		interface Worker {

			// Этот вызов блокирует поток
			fun doWork()
		}
	}

	object Task4 {

		/**
		 * Преобразовать [source] в Completable.
		 */
		fun solve(source: Single<String>): Completable = Completable.fromSingle(source)

	}

	object Task5 {

		/**
		 * Последовательно выполнить [first], [second] и [third].
		 */
		fun solve(first: Completable, second: Completable, third: Completable): Completable = Completable.concatArray(first,second,third)
	}

	object Task6 {

		/**
		 * Переключить операторы, которые будут идти ниже по цепочке на [scheduler].
		 */
		fun solve(source: Completable, scheduler: Scheduler): Completable = source.observeOn(scheduler)
	}

	object Task7 {

		/**
		 * Переключить выполнение всей цепочки на [scheduler].
		 */
		fun solve(source: Completable, scheduler: Scheduler): Completable = Completable.ambArray(source).subscribeOn(scheduler)
	}
}