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
import kr.co.override.routinizer.databinding.FragmentPostBinding
import kr.co.override.routinizer.network.dapi.challenge
import kr.co.override.routinizer.view.adapter.MyChallengeRecyclerAdapter
import kr.co.override.routinizer.viewmodel.fragment.PostViewModel

class PostFragment : Fragment() {
    private lateinit var binding: FragmentPostBinding
    lateinit var postViewModel: PostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_post,
            container,
            false
        )
        initRecycler()
        performViewModel()

        with(postViewModel) {

        }

        return binding.root
    }

    private fun performViewModel() {
        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        postViewModel = PostViewModel()
        binding.vm = postViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }

    private fun initRecycler() {
        var challengeList = ArrayList<challenge>()
        val myChallengeRecyclerAdapter = MyChallengeRecyclerAdapter(viewLifecycleOwner)
        binding.rcMychallenge.adapter = myChallengeRecyclerAdapter

        challengeList.apply {
            add(challenge("", "100", "매일 3번 이 닦기", "확인하기"))
            add(challenge("", "100", "매일 3번 이 닦기", "확인하기"))
            add(challenge("", "100", "매일 3번 이 닦기", "확인하기"))
            add(challenge("", "100", "매일 3번 이 닦기", "확인하기"))
            add(challenge("", "100", "매일 3번 이 닦기", "확인하기"))
            add(challenge("", "100", "매일 3번 이 닦기", "확인하기"))
            add(challenge("", "100", "매일 3번 이 닦기", "확인하기"))
            add(challenge("", "100", "매일 3번 이 닦기", "확인하기"))
            add(challenge("", "100", "매일 3번 이 닦기", "확인하기"))
            add(challenge("", "100", "매일 3번 이 닦기", "확인하기"))
        }

        myChallengeRecyclerAdapter.challengeList = challengeList
        myChallengeRecyclerAdapter.notifyDataSetChanged()
    }
}