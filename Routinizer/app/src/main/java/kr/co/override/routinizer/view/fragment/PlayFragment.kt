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
import kr.co.override.routinizer.viewmodel.fragment.HomeViewModel
import kr.co.override.routinizer.viewmodel.fragment.PlayViewModel

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