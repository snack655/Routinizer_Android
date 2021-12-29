package kr.co.override.routinizer.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kr.co.override.routinizer.R
import kr.co.override.routinizer.databinding.FragmentPostImgBinding
import kr.co.override.routinizer.databinding.FragmentPostTagBinding
import kr.co.override.routinizer.databinding.FragmentPostTitleBinding
import kr.co.override.routinizer.viewmodel.fragment.HomeViewModel
import kr.co.override.routinizer.viewmodel.fragment.PostImgViewModel
import kr.co.override.routinizer.viewmodel.fragment.PostTagViewModel
import kr.co.override.routinizer.viewmodel.fragment.PostTitleViewModel

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
        }
        return binding.root
    }

    private fun performViewModel() {
        postImgViewModel = ViewModelProvider(this).get(PostImgViewModel::class.java)
        postImgViewModel = PostImgViewModel()
        binding.vm = postImgViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}