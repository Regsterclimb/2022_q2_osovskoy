package com.example.a2022_q2_osovskoy.data.datasourse.local

import com.example.a2022_q2_osovskoy.data.datasourse.local.database.LoansDao
import com.example.a2022_q2_osovskoy.data.datasourse.local.database.model.LoanEntity
import javax.inject.Inject

class LoansLocalDataSourceImpl @Inject constructor(private val loansDao: LoansDao) :
    LoansLocalDataSource {

    override suspend fun deleteAll() {
        loansDao.deleteAll()
    }

    override suspend fun insertLoan(loanEntity: LoanEntity) {
        loansDao.insertLoan(loanEntity)
    }

    override suspend fun insertAll(loansList: List<LoanEntity>) {
        loansDao.insertAll(loansList)
    }

    override suspend fun getAll(): List<LoanEntity> = loansDao.getAll()

    override suspend fun getById(loanId: Long): LoanEntity = loansDao.getById(loanId)
}