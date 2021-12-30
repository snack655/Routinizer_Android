package kr.co.override.routinizer.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
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
                binding.tvPoint.text = point.value
                binding.tvSchool.text = school.value
                binding.tvPlayingC.text = continuous.value.toString()
                binding.tvPlaying.text = count.value.toString()
                var reImage: String = "http://192.168.177.67:4000/uploads/${avatar.value}"
                Glide.with(binding.root)
                    .load(reImage)
                    .error(R.drawable.default_user)
                    .centerCrop()
                    .into(binding.ivProfile)
                when(grade.value){
                    -2 -> {
                        binding.tvTier.text = "히키코모리"
                        binding.ivTier.setImageResource(R.drawable.ic_hiki_h)
                    }
                    -1 -> {
                        binding.tvTier.text = "게으름뱅이"
                        binding.ivTier.setImageResource(R.drawable.ic_lazy_h)
                    }
                    0 -> {
                        binding.tvTier.text = "일반인"
                        binding.ivTier.setImageResource(R.drawable.ic_human_h)
                    }
                    1 -> {
                        binding.tvTier.text = "성실이"
                        binding.ivTier.setImageResource(R.drawable.ic_head_h)
                    }
                    2 -> {
                        binding.tvTier.text = "바른생활 루틴이"
                        binding.ivTier.setImageResource(R.drawable.ic_rou_h)
                    }
                    3 -> {
                        binding.tvTier.text = "불붙은 챌린지"
                        binding.ivTier.setImageResource(R.drawable.ic_god_h)
                    }
                }

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