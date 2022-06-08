package com.example.a2022_q2_osovskoy.data.model.network

import com.google.gson.annotations.SerializedName

data class LoansResponse(

	@field:SerializedName("Response")
	val loansResponse: List<LoanResponse>
)


