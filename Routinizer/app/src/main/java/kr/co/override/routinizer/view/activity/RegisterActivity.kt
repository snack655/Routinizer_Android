package kr.co.override.routinizer.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kr.co.override.routinizer.R
import kr.co.override.routinizer.databinding.ActivityRegisterBinding
import kr.co.override.routinizer.viewmodel.activity.RegisterViewModel

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()

        with(registerViewModel) {
            onRegisterEvent.observe(this@RegisterActivity, {
                val id : String = findViewById<EditText>(R.id.register_id).text.toString()
                val pw : String = findViewById<EditText>(R.id.register_pw).text.toString()
                val Rpw : String = findViewById<EditText>(R.id.register_Rpw).text.toString()
                //if (아이디가 중복이 아니며, 비밀번호가 일치 할 때 실행)
                val intent = Intent(this@RegisterActivity, ProfileSettingsActivity::class.java)
                startActivity(intent)
                //else 토스트 메시지 띄움
            })
            toLoginEvent.observe(this@RegisterActivity, {
                val intent = Intent(this@RegisterActivity,LoginActivity::class.java)
                startActivity(intent)
            })
        }
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        binding.vm = registerViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}