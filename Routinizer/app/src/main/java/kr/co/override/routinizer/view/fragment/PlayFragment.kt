package kr.co.override.routinizer.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kr.co.override.routinizer.R
import kr.co.override.routinizer.databinding.FragmentHomeBinding
import kr.co.override.routinizer.databinding.FragmentPlayBinding
import kr.co.override.routinizer.databinding.FragmentPostBinding
import kr.co.override.routinizer.network.dapi.challenge
import kr.co.override.routinizer.view.adapter.MyChallengeRecyclerAdapter
import kr.co.override.routinizer.viewmodel.fragment.HomeViewModel
import kr.co.override.routinizer.viewmodel.fragment.PlayViewModel
import kr.co.override.routinizer.viewmodel.fragment.PostViewModel

class PlayFragment : Fragment() {
    private lateinit var binding: FragmentPlayBinding
    lateinit var playViewModel: PlayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_play,
            container,
            false
        )
        initRecycler()

        performViewModel()
        return binding.root
    }

    private fun performViewModel() {
        playViewModel = ViewModelProvider(this).get(PlayViewModel::class.java)
        playViewModel = PlayViewModel()
        binding.vm = playViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }

    private fun initRecycler() {
        var challengeList = ArrayList<challenge>()
        val myChallengeRecyclerAdapter = MyChallengeRecyclerAdapter(viewLifecycleOwner)
        binding.rcMychallenge.adapter = myChallengeRecyclerAdapter

        challengeList.apply {
            add(challenge("", "10일 연속", "매일 3번 이 닦기", "인증하기"))
            add(challenge("", "10일 연속", "매일 3번 이 닦기", "인증하기"))
            add(challenge("", "10일 연속", "매일 3번 이 닦기", "인증하기"))
            add(challenge("", "10일 연속", "매일 3번 이 닦기", "인증하기"))
            add(challenge("", "10일 연속", "매일 3번 이 닦기", "인증하기"))
            add(challenge("", "10일 연속", "매일 3번 이 닦기", "인증하기"))
            add(challenge("", "10일 연속", "매일 3번 이 닦기", "인증하기"))
            add(challenge("", "10일 연속", "매일 3번 이 닦기", "인증하기"))
            add(challenge("", "10일 연속", "매일 3번 이 닦기", "인증하기"))
            add(challenge("", "10일 연속", "매일 3번 이 닦기", "인증하기"))
        }

        myChallengeRecyclerAdapter.challengeList = challengeList
        myChallengeRecyclerAdapter.notifyDataSetChanged()
    }
}