package kr.co.override.routinizer.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
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
                val id : String = binding.registerId.text.toString()
                val pw : String = binding.registerPw.text.toString()
                val Rpw : String = binding.registerRpw.text.toString()
                //if (아이디가 중복이 아니며, 비밀번호가 일치 할 때 실행)

                if (pw == Rpw) {
                    val intent = Intent(this@RegisterActivity, ProfileSettingsActivity::class.java)
                    intent.putExtra("id", id)
                    intent.putExtra("pw", pw)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@RegisterActivity, "비밀번호가 같지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            })
            onLoginEvent.observe(this@RegisterActivity, {
                val intent = Intent(this@RegisterActivity,LoginActivity::class.java)
                startActivity(intent)
                finish()
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