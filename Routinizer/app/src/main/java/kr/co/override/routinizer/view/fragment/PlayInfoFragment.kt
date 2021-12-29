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
import android.R.string.no
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import kr.co.override.routinizer.databinding.FragmentPlayInfoBinding
import kr.co.override.routinizer.viewmodel.fragment.*


class PlayInfoFragment : Fragment() {
    private lateinit var binding: FragmentPlayInfoBinding
    lateinit var PlayInfoViewModel: PlayInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_play_info,
            container,
            false
        )

        performViewModel()

        with(PlayInfoViewModel) {
            onNextEvent.observe(this@PlayInfoFragment, {
                findNavController().navigate(R.id.action_postGoodFragment_to_main_post)
            })
            onBackEvent.observe(this@PlayInfoFragment, {
                findNavController().navigate(R.id.action_postGoodFragment_to_postImgFragment)
            })
            onSuccessEvent.observe(this@PlayInfoFragment, {
                binding.tvInfoTag.text = category.value
                binding.tvInfoContinue.text = continuous.value.toString()
                binding.tvInfoPlaying.text = participationCount.value.toString()
                binding.tvInfo.text = title.value
                binding.tvInfoInfo.text = benefit.value

                var reImage: String = "http://192.168.52.67:4000/uploads/${image}"

                Glide.with(binding.root)
                    .load(reImage)
                    .error(R.drawable.noimg)
                    .centerCrop()
                    .into(binding.ivInfoImg)
            })
        }
        return binding.root
    }

    private fun performViewModel() {
        PlayInfoViewModel = ViewModelProvider(this).get(PlayInfoViewModel::class.java)
        PlayInfoViewModel = PlayInfoViewModel()
        binding.vm = PlayInfoViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}