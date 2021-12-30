package kr.co.override.routinizer.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import kr.co.override.routinizer.R
import kr.co.override.routinizer.databinding.FragmentHomeBinding
import kr.co.override.routinizer.view.adapter.HotRecyclerAdapter
import kr.co.override.routinizer.view.adapter.NewRecyclerAdapter
import kr.co.override.routinizer.view.adapter.PowerRecyclerAdapter
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
        val hotRecyclerAdapter = HotRecyclerAdapter(viewLifecycleOwner)
        val newRecyclerAdapter = NewRecyclerAdapter(viewLifecycleOwner)
        val powerRecyclerAdapter = PowerRecyclerAdapter(viewLifecycleOwner)
        binding.recyclerHotPost.adapter = hotRecyclerAdapter
        binding.recyclerNewPost.adapter = newRecyclerAdapter
        binding.recyclerPowerPost.adapter = powerRecyclerAdapter

        with(homeViewModel) {
            getMainPosts()

            message.observe(this@HomeFragment.viewLifecycleOwner, {
                Toast.makeText(context, "${message.value}", Toast.LENGTH_SHORT).show()
            })

            hotPostsList.observe(this@HomeFragment.viewLifecycleOwner, {
                hotRecyclerAdapter.postList = it
                hotRecyclerAdapter.notifyDataSetChanged()
            })

            newPostsList.observe(this@HomeFragment.viewLifecycleOwner, {
                newRecyclerAdapter.postList = it
                newRecyclerAdapter.notifyDataSetChanged()
            })

            exercisePostsList.observe(this@HomeFragment.viewLifecycleOwner, {
                powerRecyclerAdapter.postList = it
                powerRecyclerAdapter.notifyDataSetChanged()
            })

            HotRecyclerAdapter.onHotPostClick.observe(this@HomeFragment, {
                val action = HomeFragmentDirections.actionMainHomeToPlayInfoFragment(it)
                findNavController().navigate(action)
            })
        }
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