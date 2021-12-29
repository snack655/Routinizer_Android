package kr.co.override.routinizer.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kr.co.override.routinizer.R
import kr.co.override.routinizer.databinding.ActivityProfileSettingsBinding
import kr.co.override.routinizer.viewmodel.activity.ProfileSettingsViewModel

class ProfileSettingsActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileSettingsBinding
    lateinit var profileViewModel: ProfileSettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()

        val intent = intent
        val idResult = intent.getStringExtra("id")
        val pwResult = intent.getStringExtra("pw")

        with(profileViewModel) {
            id.value = idResult
            pw.value = pwResult

            onProfileEvent.observe(this@ProfileSettingsActivity, {
                //닉네임을 서버에 넘겨줌
                val intent = Intent(this@ProfileSettingsActivity, LoginActivity::class.java)
                finishAffinity()
                startActivity(intent)
            })

            onBackEvent.observe(this@ProfileSettingsActivity, {
                finish()
            })
        }

    }



    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile_settings)
        profileViewModel = ViewModelProvider(this).get(ProfileSettingsViewModel::class.java)
        binding.vm = profileViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }



}