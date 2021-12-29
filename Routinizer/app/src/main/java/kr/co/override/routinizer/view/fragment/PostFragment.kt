package kr.co.override.routinizer.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kr.co.override.routinizer.R
import kr.co.override.routinizer.databinding.FragmentPostBinding
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
        performViewModel()

        val myChallengeRecyclerAdapter = MyChallengeRecyclerAdapter(viewLifecycleOwner)
        binding.rcMychallenge.adapter = myChallengeRecyclerAdapter

        with(postViewModel) {
            getMyPosts()

            message.observe(this@PostFragment.viewLifecycleOwner, {
                Toast.makeText(context, "${message.value}", Toast.LENGTH_SHORT).show()
            })

            myPostsList.observe(this@PostFragment.viewLifecycleOwner, {
                myChallengeRecyclerAdapter.challengeList = it
                myChallengeRecyclerAdapter.notifyDataSetChanged()
            })
            onNextEvent.observe(this@PostFragment, {
                findNavController().navigate(kr.co.override.routinizer.R.id.action_main_post_to_postTagFragment)
            })
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
}