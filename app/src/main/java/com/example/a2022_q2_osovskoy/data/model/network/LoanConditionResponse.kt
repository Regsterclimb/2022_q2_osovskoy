package com.example.a2022_q2_osovskoy.data.model.network

import com.google.gson.annotations.SerializedName

data class LoanConditionResponse(

	@field:SerializedName("period")
	val period: Int,

	@field:SerializedName("maxAmount")
	val maxAmount: Int,

	@field:SerializedName("percent")
	val percent: Double
)
