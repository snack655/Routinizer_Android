package kr.co.override.routinizer.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        performViewModel()

        val myChallengeRecyclerAdapter = MyChallengeRecyclerAdapter(viewLifecycleOwner)
        binding.rcMychallenge.adapter = myChallengeRecyclerAdapter

        with(playViewModel) {
            getMyInvitedPosts()

            message.observe(this@PlayFragment.viewLifecycleOwner, {
                Toast.makeText(context, "${message.value}", Toast.LENGTH_SHORT).show()
            })

            myInvitedPostsList.observe(this@PlayFragment.viewLifecycleOwner, {
                myChallengeRecyclerAdapter.challengeList = it
                myChallengeRecyclerAdapter.notifyDataSetChanged()
            })
        }
        return binding.root
    }

    private fun performViewModel() {
        playViewModel = ViewModelProvider(this).get(PlayViewModel::class.java)
        playViewModel = PlayViewModel()
        binding.vm = playViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}