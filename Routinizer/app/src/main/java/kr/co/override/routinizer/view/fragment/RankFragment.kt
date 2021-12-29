package kr.co.override.routinizer.view.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kr.co.override.routinizer.R
import kr.co.override.routinizer.databinding.FragmentRankBinding
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
        performViewModel()

        val rankRecyclerAdapter = RankRecyclerAdapter(viewLifecycleOwner)
        binding.rcRank.adapter = rankRecyclerAdapter

        with(rankViewModel) {
            getAllRanking()

            allRankingList.observe(this@RankFragment.viewLifecycleOwner, {
                rankRecyclerAdapter.rankList = it
                rankRecyclerAdapter.notifyDataSetChanged()
            })

            gradeRankingList.observe(this@RankFragment.viewLifecycleOwner, {
                rankRecyclerAdapter.rankList = it
                rankRecyclerAdapter.notifyDataSetChanged()
            })

            status.observe(this@RankFragment.viewLifecycleOwner, {
                if (status.value == 0) {
                    binding.tvAllRanking.setTextColor(Color.parseColor("#000000"))
                    binding.tvGradeRanking.setTextColor(Color.parseColor("#A6A6A6"))
                } else if (status.value == 1) {
                    binding.tvGradeRanking.setTextColor(Color.parseColor("#000000"))
                    binding.tvAllRanking.setTextColor(Color.parseColor("#A6A6A6"))
                }
            })
        }
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