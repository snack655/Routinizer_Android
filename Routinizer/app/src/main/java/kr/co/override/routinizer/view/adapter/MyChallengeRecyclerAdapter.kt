package kr.co.override.routinizer.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.co.override.routinizer.R
import kr.co.override.routinizer.databinding.ItemChallengeRecyclerBinding
import kr.co.override.routinizer.databinding.ItemPostRecyclerBinding
import kr.co.override.routinizer.databinding.ItemRankRecyclerBinding
import kr.co.override.routinizer.network.dapi.challenge
import kr.co.override.routinizer.network.dapi.post
import kr.co.override.routinizer.network.dapi.rank

class MyChallengeRecyclerAdapter(val lifecycleOwner: LifecycleOwner) :
    RecyclerView.Adapter<MyChallengeRecyclerAdapter.ChallengeViewHolder>() {

    var challengeList: List<challenge> = ArrayList<challenge>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChallengeViewHolder {
        return ChallengeViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_challenge_recycler,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        holder.bind(challengeList[position])
    }

    override fun getItemCount(): Int = challengeList.size

    class ChallengeViewHolder(private val binding: ItemChallengeRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(cchallenge: challenge){
                with(cchallenge) {
                    binding.tvCount.text = cnt
                    binding.tvSentence.text = sentence
                    binding.btnCheck.text = check
                    Glide.with(binding.root)
                        .load(img)
                        .error(R.drawable.noimg)
                        .centerCrop()
                        .into(binding.ivChallenge)
                }
            }

        }
}