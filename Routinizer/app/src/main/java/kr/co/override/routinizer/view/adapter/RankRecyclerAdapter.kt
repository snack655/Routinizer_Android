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
import kr.co.override.routinizer.network.model.response.user

class RankRecyclerAdapter(val lifecycleOwner: LifecycleOwner) :
    RecyclerView.Adapter<RankRecyclerAdapter.RankViewHolder>() {

    var rankList: List<user> = ArrayList<user>()

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
        holder.bind(rankList[position], position)
    }

    override fun getItemCount(): Int = rankList.size

    class RankViewHolder(private val binding: ItemRankRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(user: user, idx : Int){
                with(user) {
                    binding.tvRank.text = idx.toString()
                    binding.tvRankName.text = name
                    binding.tvRankSchool.text = school

                    var reAvatar: String = "http://192.168.52.67:4000/uploads/${avatar}"

                    Glide.with(binding.root)
                        .load(reAvatar)
                        .error(R.drawable.default_user)
                        .centerCrop()
                        .into(binding.ivProfileRank)
                }
            }

        }
}