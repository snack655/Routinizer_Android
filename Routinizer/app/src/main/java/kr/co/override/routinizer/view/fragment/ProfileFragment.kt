package kr.co.override.routinizer.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kr.co.override.routinizer.R
import kr.co.override.routinizer.databinding.FragmentProfileBinding
import kr.co.override.routinizer.databinding.FragmentRankBinding
import kr.co.override.routinizer.viewmodel.fragment.ProfileViewModel
import kr.co.override.routinizer.viewmodel.fragment.RankViewModel

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_profile,
            container,
            false
        )
        performViewModel()

        with(profileViewModel){
            getMyProfile()
            onSuccessEvent.observe(this@ProfileFragment, {
                binding.tvName.text = name.value
            })
        }


        return binding.root
    }

    private fun performViewModel() {
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        profileViewModel = ProfileViewModel()
        binding.vm = profileViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}