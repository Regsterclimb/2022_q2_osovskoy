package com.example.a2022_q2_osovskoy.domain.entities

interface Animal {

	val name: String
	val age: String

	fun voice(): String
}