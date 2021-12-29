package kr.co.override.routinizer.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
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
        performViewModel()

        with(postTagViewModel) {
            onNextEvent.observe(this@PostTagFragment, {
                if (PostGoodFragment.categoryB == "") {
                    Toast.makeText(context, "카테고리를 선택해 주세요", Toast.LENGTH_SHORT).show()
                } else {
                    findNavController().navigate(R.id.action_postTagFragment_to_postTitleFragment)
                }
            })
            onBackEvent.observe(this@PostTagFragment, {
                findNavController().navigate(R.id.action_postTagFragment_to_main_post)
            })
        }
        binding.radioGroup.setOnCheckedChangeListener{ group, checkId ->
            when(checkId){
                R.id.radio_1 -> PostGoodFragment.categoryB = "regular"
                R.id.radio_2 -> PostGoodFragment.categoryB = "exercise"
                R.id.radio_3 -> PostGoodFragment.categoryB = "hobby"
                R.id.radio_4 -> PostGoodFragment.categoryB = "food"
                R.id.radio_5 -> PostGoodFragment.categoryB = "eco"
                R.id.radio_6 -> PostGoodFragment.categoryB = "mindset"
                R.id.radio_7 -> PostGoodFragment.categoryB = "etc"
            }
        }
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