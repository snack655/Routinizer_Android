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
import kr.co.override.routinizer.databinding.FragmentPostTagBinding
import kr.co.override.routinizer.databinding.FragmentPostTitleBinding
import kr.co.override.routinizer.viewmodel.fragment.HomeViewModel
import kr.co.override.routinizer.viewmodel.fragment.PostTagViewModel
import kr.co.override.routinizer.viewmodel.fragment.PostTitleViewModel

class PostTitleFragment : Fragment() {
    private lateinit var binding: FragmentPostTitleBinding
    lateinit var postTitleViewModel: PostTitleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_post_title,
            container,
            false
        )

        performViewModel()

        with(postTitleViewModel) {
            onNextEvent.observe(this@PostTitleFragment, {
                findNavController().navigate(R.id.action_postTitleFragment_to_postImgFragment)
            })
            onBackEvent.observe(this@PostTitleFragment, {
                findNavController().navigate(R.id.action_postTitleFragment_to_postTagFragment)
            })
        }


        return binding.root
    }

    private fun performViewModel() {
        postTitleViewModel = ViewModelProvider(this).get(PostTitleViewModel::class.java)
        postTitleViewModel = PostTitleViewModel()
        binding.vm = postTitleViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}