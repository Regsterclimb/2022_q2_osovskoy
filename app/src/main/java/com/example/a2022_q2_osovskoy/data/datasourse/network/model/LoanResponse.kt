package com.example.a2022_q2_osovskoy.data.datasourse.network.model

import com.google.gson.annotations.SerializedName

data class LoanResponse(

    @field:SerializedName("date")
    val date: String,

    @field:SerializedName("firstName")
    val firstName: String,

    @field:SerializedName("lastName")
    val lastName: String,

    @field:SerializedName("amount")
    val amount: Double,

    @field:SerializedName("period")
    val period: Int,

    @field:SerializedName("phoneNumber")
    val phoneNumber: String,

    @field:SerializedName("id")
    val id: Long,

    @field:SerializedName("state")
    val state: String,

    @field:SerializedName("percent")
    val percent: Double,
)
