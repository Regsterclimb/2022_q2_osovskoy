package com.example.a2022_q2_osovskoy.data.datasourse.local

import android.util.Log
import com.example.a2022_q2_osovskoy.data.datasourse.local.database.LoansDataBase
import com.example.a2022_q2_osovskoy.data.datasourse.local.database.model.LoanEntity
import javax.inject.Inject

class LoansLocalDataSourceImpl @Inject constructor(private val loansDataBase: LoansDataBase) :
    LoansLocalDataSource {
    override suspend fun insertLoan(loanEntity: LoanEntity) {
        loansDataBase.LoansDao().insertLoan(loanEntity)
    }

    override suspend fun insertAll(loansList: List<LoanEntity>) {
        Log.d("LoansLocalDataSourceImpl", loansList.toString())
        loansDataBase.LoansDao().insertAll(loansList)
    }

    override suspend fun getAll(): List<LoanEntity> {
        Log.d("LoansLocalDataSourceImpl", loansDataBase.LoansDao().getAll().toString())
        return loansDataBase.LoansDao().getAll()
    }

        override suspend fun getById(loanId: Long): LoanEntity =
            loansDataBase.LoansDao().getLoanById(loanId)
    }