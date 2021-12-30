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
import android.R.string.no
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import kr.co.override.routinizer.databinding.FragmentPlayInfoBinding
import kr.co.override.routinizer.viewmodel.fragment.*


class PlayInfoFragment : Fragment() {
    private lateinit var binding: FragmentPlayInfoBinding
    lateinit var playInfoViewModel: PlayInfoViewModel
    val id: PlayInfoFragmentArgs by navArgs()

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

        with(playInfoViewModel) {
            val _id = id.postId
            getDetailPost(_id)
            __id.value = _id

            onNextEvent.observe(this@PlayInfoFragment, {
                findNavController().navigate(R.id.action_playInfoFragment_to_main_home)
            })
            onBackEvent.observe(this@PlayInfoFragment, {
                findNavController().navigate(R.id.action_playInfoFragment_to_main_home)
            })
            onSuccessEvent.observe(this@PlayInfoFragment, {
                binding.tvInfoTag.text = category.value
                binding.tvInfoContinue.text = continuous.value.toString()
                binding.tvInfoPlaying.text = participationCount.value.toString()
                binding.tvInfo.text = title.value
                binding.tvInfoInfo.text = benefit.value

                Log.d("TestTest", "onCreateView: ${category.value.toString()}")

                var reImage: String = "http://192.168.177.67:4000/uploads/${image}"

                Glide.with(binding.root)
                    .load(reImage)
                    .error(R.drawable.noimg)
                    .centerCrop()
                    .into(binding.ivInfoImg)
            })

            message.observe(this@PlayInfoFragment.viewLifecycleOwner, {
                Toast.makeText(context, "${message.value}", Toast.LENGTH_SHORT).show()
            })
        }
        return binding.root
    }

    private fun performViewModel() {
        playInfoViewModel = ViewModelProvider(this).get(PlayInfoViewModel::class.java)
        playInfoViewModel = PlayInfoViewModel()
        binding.vm = playInfoViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}