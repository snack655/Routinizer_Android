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
import kr.co.override.routinizer.network.dapi.rank
import kr.co.override.routinizer.view.adapter.RankRecyclerAdapter
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
        initRecycler()

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

    private fun initRecycler() {
        var rankList = ArrayList<rank>()
        val rankRecyclerAdapter = RankRecyclerAdapter(viewLifecycleOwner)
        binding.rcRank.adapter = rankRecyclerAdapter

        rankList.apply {
            add(rank("", 1, "스미스 킴", "현풍고등학교"))
            add(rank("", 2, "안경순", "대구소프트웨어마이스터고등학교"))
            add(rank("", 3, "이경변태", "사대부고등학교"))
            add(rank("", 4, "황순원", "국어책고등학교"))
            add(rank("", 5, "김철민", "제주국제고등학교"))
            add(rank("", 6, "백자현", "서울고등학교"))
        }

        rankRecyclerAdapter.rankList = rankList
        rankRecyclerAdapter.notifyDataSetChanged()
    }
}