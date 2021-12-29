package kr.co.override.routinizer.view.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import kr.co.override.routinizer.R
import kr.co.override.routinizer.databinding.ActivityProfileSettingsBinding
import kr.co.override.routinizer.viewmodel.activity.ProfileSettingsViewModel
import java.io.File

class ProfileSettingsActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileSettingsBinding
    lateinit var profileSettingsViewModel: ProfileSettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()

        val intent = intent
        val idResult = intent.getStringExtra("id")
        val pwResult = intent.getStringExtra("pw")

        with(profileSettingsViewModel) {
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

            onImageEvent.observe(this@ProfileSettingsActivity, {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                intent.type = "image/*"
                startActivityForResult(intent, 10)
            })

            message.observe(this@ProfileSettingsActivity, {
                Toast.makeText(this@ProfileSettingsActivity, "${message.value}", Toast.LENGTH_SHORT).show()
            })
            
            onConfirmEvent.observe(this@ProfileSettingsActivity, {
                Toast.makeText(this@ProfileSettingsActivity, "이미지가 변경되었습니다!", Toast.LENGTH_SHORT).show()
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 10 && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                val imageUri: Uri? = data.data
                profileSettingsViewModel.img.value?.add( File(imageUri?.let { getRealPathFromURI(it).path }))
                if (imageUri != null) {
                    Glide.with(binding.root)
                        .load(imageUri)
                        .error(R.drawable.default_user)
                        .centerCrop()
                        .into(binding.profileImage1)
                }
            }
        }
    }

    private fun getRealPathFromURI(uri: Uri): Uri {
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = applicationContext?.contentResolver?.query(uri, filePathColumn, null, null, null)
        cursor?.moveToFirst()
        val columnIndex = cursor?.getColumnIndex(filePathColumn[0])
        val picturePath = columnIndex?.let { cursor.getString(it) }
        cursor?.close()
        return Uri.fromFile(File(picturePath ?: ""))
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile_settings)
        profileSettingsViewModel = ViewModelProvider(this).get(ProfileSettingsViewModel::class.java)
        binding.vm = profileSettingsViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }



}