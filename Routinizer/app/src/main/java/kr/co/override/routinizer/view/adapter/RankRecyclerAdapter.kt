package kr.co.override.routinizer.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.co.override.routinizer.R
import kr.co.override.routinizer.databinding.ItemRankRecyclerBinding
import kr.co.override.routinizer.network.dapi.rank

class RankRecyclerAdapter(val lifecycleOwner: LifecycleOwner) :
    RecyclerView.Adapter<RankRecyclerAdapter.RankViewHolder>() {

    var rankList: List<rank> = ArrayList<rank>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RankViewHolder {
        return RankViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_rank_recycler,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RankViewHolder, position: Int) {
        holder.bind(rankList[position])
    }

    override fun getItemCount(): Int = rankList.size

    class RankViewHolder(private val binding: ItemRankRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(rrank: rank){
                with(rrank) {
                    binding.tvRank.text = Rank.toString()
                    binding.tvRankName.text = nickname
                    binding.tvRankSchool.text = school
                    Glide.with(binding.root)
                        .load(img)
                        .error(R.drawable.default_user)
                        .centerCrop()
                        .into(binding.ivProfileRank)
                }
            }

        }
}