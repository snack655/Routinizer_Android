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
import kr.co.override.routinizer.databinding.FragmentPostGoodBinding
import kr.co.override.routinizer.databinding.FragmentPostTagBinding
import kr.co.override.routinizer.databinding.FragmentPostTitleBinding
import kr.co.override.routinizer.viewmodel.fragment.HomeViewModel
import kr.co.override.routinizer.viewmodel.fragment.PostGoodViewModel
import kr.co.override.routinizer.viewmodel.fragment.PostTagViewModel
import kr.co.override.routinizer.viewmodel.fragment.PostTitleViewModel
import android.R.string.no
import androidx.fragment.app.FragmentManager


class PostGoodFragment : Fragment() {

    companion object {
        var titleB = ""
        var categoryB = ""
        var imageB = ""
    }

    private lateinit var binding: FragmentPostGoodBinding
    lateinit var postGoodViewModel: PostGoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_post_good,
            container,
            false
        )

        performViewModel()

        with(postGoodViewModel) {

            onInstanceEvent.observe(this@PostGoodFragment, {
                title.value = titleB
                category.value = categoryB
                image.value = imageB
            })

            onNextEvent.observe(this@PostGoodFragment, {
                findNavController().navigate(R.id.action_postGoodFragment_to_main_post)
            })
            onBackEvent.observe(this@PostGoodFragment, {
                findNavController().navigate(R.id.action_postGoodFragment_to_postImgFragment)
            })


        }
        return binding.root
    }

    private fun performViewModel() {
        postGoodViewModel = ViewModelProvider(this).get(PostGoodViewModel::class.java)
        postGoodViewModel = PostGoodViewModel()
        binding.vm = postGoodViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}