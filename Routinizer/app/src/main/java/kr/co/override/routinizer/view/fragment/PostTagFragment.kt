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
import kr.co.override.routinizer.viewmodel.fragment.HomeViewModel
import kr.co.override.routinizer.viewmodel.fragment.PostTagViewModel

class PostTagFragment : Fragment() {
    private lateinit var binding: FragmentPostTagBinding
    lateinit var postTagViewModel: PostTagViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_post_tag,
            container,
            false
        )

        with(postTagViewModel) {
            onNextEvent.observe(this@PostTagFragment, {
                findNavController().navigate(R.id.action_postTagFragment_to_main_post)
            })
        }

        performViewModel()
        return binding.root
    }

    private fun performViewModel() {
        postTagViewModel = ViewModelProvider(this).get(PostTagViewModel::class.java)
        postTagViewModel = PostTagViewModel()
        binding.vm = postTagViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}