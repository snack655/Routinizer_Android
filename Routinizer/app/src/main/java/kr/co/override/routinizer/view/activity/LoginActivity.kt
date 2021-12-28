package kr.co.override.routinizer.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kr.co.override.routinizer.R
import kr.co.override.routinizer.databinding.ActivityLoginBinding
import kr.co.override.routinizer.viewmodel.activity.LoginViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        
        with(loginViewModel) {
            onLoginEvent.observe(this@LoginActivity, {
                val id : String = findViewById<EditText>(R.id.login_id).text.toString()
                val pw : String = findViewById<EditText>(R.id.login_pw).text.toString()
                //if(아이디와 비밀번호가 서버의 데이터와 일치 할 때)
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                finishAffinity()
                startActivity(intent)
                //else 토스트 메시지 띄움
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