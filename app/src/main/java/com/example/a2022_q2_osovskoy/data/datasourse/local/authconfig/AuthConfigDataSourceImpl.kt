package com.example.a2022_q2_osovskoy.data.datasourse.local.authconfig

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.a2022_q2_osovskoy.di.annotations.BasicSharedPrefs
import com.example.a2022_q2_osovskoy.domain.entity.LoginConfigState
import javax.inject.Inject

class AuthConfigDataSourceImpl @Inject constructor(
    @BasicSharedPrefs private val sharedPreferences: SharedPreferences,
) : AuthConfigDataSource {

    companion object {
        const val LOGIN_CONFIG_KEY = "login_config_key"
        const val LOGIN_DEFAULT_VALUE = false
    }

    override fun get(): LoginConfigState =
        LoginConfigState(sharedPreferences.getBoolean(LOGIN_CONFIG_KEY, LOGIN_DEFAULT_VALUE))

    override fun update(loginConfigState: LoginConfigState) {
        sharedPreferences.edit {
            putBoolean(LOGIN_CONFIG_KEY, loginConfigState.isScreenLogin)
        }
    }
}