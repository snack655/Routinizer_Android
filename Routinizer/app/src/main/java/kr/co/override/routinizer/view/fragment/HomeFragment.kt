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
import kr.co.override.routinizer.network.dapi.post
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
        initRecycler()

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

    private fun initRecycler() {
        var hotList = ArrayList<post>()
        var newList = ArrayList<post>()
        var powerList = ArrayList<post>()
        val hotRecyclerAdapter = HotRecyclerAdapter(viewLifecycleOwner)
        val newRecyclerAdapter = NewRecyclerAdapter(viewLifecycleOwner)
        val powerRecyclerAdapter = PowerRecyclerAdapter(viewLifecycleOwner)
        binding.recyclerHotPost.adapter = hotRecyclerAdapter
        binding.recyclerNewPost.adapter = newRecyclerAdapter
        binding.recyclerPowerPost.adapter = powerRecyclerAdapter

        hotList.apply {
            add(post("", "하루 4번 양치하기", 98, "규칙"))
            add(post("", "독서 하루 1시간 이상", 52, "규칙"))
            add(post("", "아침 6시에 기상하기", 43, "규칙"))
            add(post("", "아침 밥을 챙겨먹기", 130, "식습관"))
            add(post("", "7시간 이상 수면", 120, "기타"))
        }

        newList.apply {
            add(post("", "하루 4번 손씻기", 88, "식습관"))
            add(post("", "독서 하루 2시간 이상", 42, "기타"))
            add(post("", "아침 7시에 기상하기", 33, "규칙"))
            add(post("", "저녁 밥을 챙겨먹기", 100, "식습관"))
            add(post("", "7시간 이하 수면", 121, "기타"))
        }

        powerList.apply {
            add(post("", "하루 5번 양치하기", 99, "기탸"))
            add(post("", "독서 하루 3시간 이상", 22, "마음가짐"))
            add(post("", "아침 8시에 기상하기", 33, "규칙"))
            add(post("", "간식을 거르기", 19, "식습관"))
            add(post("", "1시간 이상 운동하기", 80, "기타"))
        }

        hotRecyclerAdapter.postList = hotList
        newRecyclerAdapter.postList = newList
        powerRecyclerAdapter.postList = powerList
        hotRecyclerAdapter.notifyDataSetChanged()
        newRecyclerAdapter.notifyDataSetChanged()
        powerRecyclerAdapter.notifyDataSetChanged()
    }
}