package kr.co.override.routinizer.view.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kr.co.override.routinizer.R
import kr.co.override.routinizer.databinding.ActivityLoginBinding
import kr.co.override.routinizer.viewmodel.activity.LoginViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var loginViewModel: LoginViewModel

    companion object {
        const val TOKEN_PREFERENCE = "TOKEN_PREFERENCES"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        
        with(loginViewModel) {
            val autoPref = getSharedPreferences(TOKEN_PREFERENCE, Activity.MODE_PRIVATE)
            id.value = autoPref.getString("id", null)
            password.value = autoPref.getString("password", null)
            if (id.value != null && password.value != null) {
                onClickLogin()
            }

            onLoginEvent.observe(this@LoginActivity, {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                finishAffinity()
                startActivity(intent)
            })

            message.observe(this@LoginActivity, {
                Toast.makeText(this@LoginActivity, "${message.value}", Toast.LENGTH_SHORT).show()
            })

            token.observe(this@LoginActivity, {
                val sharedPref = applicationContext.getSharedPreferences(TOKEN_PREFERENCE, Context.MODE_PRIVATE)

                with(sharedPref.edit()) {
                    putString("id", id.value)
                    putString("password", password.value)
                    putString("token", it)
                    apply()
                }
            })

            onBackEvent.observe(this@LoginActivity, {
                finish()
            })
        }
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.vm = loginViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}