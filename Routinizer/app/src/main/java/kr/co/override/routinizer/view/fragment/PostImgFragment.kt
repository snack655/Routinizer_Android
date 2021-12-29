package kr.co.override.routinizer.view.fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import kr.co.override.routinizer.R
import kr.co.override.routinizer.databinding.FragmentPostImgBinding
import kr.co.override.routinizer.viewmodel.fragment.PostImgViewModel

import java.io.File

class PostImgFragment : Fragment() {
    private lateinit var binding: FragmentPostImgBinding
    lateinit var postImgViewModel: PostImgViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_post_img,
            container,
            false
        )

        performViewModel()

        with(postImgViewModel) {
            onNextEvent.observe(this@PostImgFragment, {
                findNavController().navigate(R.id.action_postImgFragment_to_postGoodFragment)
            })
            onBackEvent.observe(this@PostImgFragment, {
                findNavController().navigate(R.id.action_postImgFragment_to_postTitleFragment)
            })
            onSelectEvent.observe(this@PostImgFragment, {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                intent.type = "image/*"
                startActivityForResult(intent, 10)
            })

            message.observe(this@PostImgFragment.viewLifecycleOwner, {
                Toast.makeText(context, "${message.value}", Toast.LENGTH_SHORT).show()
            })

            reImg.observe(this@PostImgFragment.viewLifecycleOwner, {
                PostGoodFragment.imageB = reImg.value.toString()
            })
        }
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 10 && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                val imageUri: Uri? = data.data
                postImgViewModel.img.value?.add( File(imageUri?.let { getRealPathFromURI(it).path }))
                if (imageUri != null) {
                    Glide.with(binding.root)
                        .load(imageUri)
                        .error(R.drawable.default_user)
                        .centerCrop()
                        .into(binding.imgPostImg)
                }
            }
        }
    }

    private fun getRealPathFromURI(uri: Uri): Uri {
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context?.contentResolver?.query(uri, filePathColumn, null, null, null)
        cursor?.moveToFirst()
        val columnIndex = cursor?.getColumnIndex(filePathColumn[0])
        val picturePath = columnIndex?.let { cursor.getString(it) }
        cursor?.close()
        return Uri.fromFile(File(picturePath ?: ""))
    }

    private fun performViewModel() {
        postImgViewModel = ViewModelProvider(this).get(PostImgViewModel::class.java)
        postImgViewModel = PostImgViewModel()
        binding.vm = postImgViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}