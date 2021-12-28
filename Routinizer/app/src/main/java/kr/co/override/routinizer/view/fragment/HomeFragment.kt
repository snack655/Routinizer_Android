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
import kr.co.override.routinizer.viewmodel.fragment.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )


        performViewModel()
        return binding.root
    }

    private fun performViewModel() {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel = HomeViewModel()
        binding.vm = homeViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}