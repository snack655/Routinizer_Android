package kr.co.override.routinizer.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kr.co.override.routinizer.R
import kr.co.override.routinizer.databinding.FragmentRankBinding
import kr.co.override.routinizer.viewmodel.fragment.RankViewModel

class RankFragment : Fragment() {
    private lateinit var binding: FragmentRankBinding
    lateinit var rankViewModel: RankViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_rank,
            container,
            false
        )


        performViewModel()
        return binding.root
    }

    private fun performViewModel() {
        rankViewModel = ViewModelProvider(this).get(RankViewModel::class.java)
        rankViewModel = RankViewModel()
        binding.vm = rankViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}