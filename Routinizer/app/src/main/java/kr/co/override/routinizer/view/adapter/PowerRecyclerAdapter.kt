package kr.co.override.routinizer.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.co.override.routinizer.R
import kr.co.override.routinizer.databinding.ItemPostRecyclerBinding
import kr.co.override.routinizer.network.model.response.challenge

class PowerRecyclerAdapter(val lifecycleOwner: LifecycleOwner) :
    RecyclerView.Adapter<PowerRecyclerAdapter.PowerViewHolder>() {

    var postList: List<challenge> = ArrayList<challenge>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PowerViewHolder {
        return PowerViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_post_recycler,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PowerViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int = postList.size

    class PowerViewHolder(private val binding: ItemPostRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(challenge: challenge){
                with(challenge) {
                    binding.tvCount.text = participantCount.toString()
                    binding.tvTag.text = category
                    binding.tvTitle.text = title

                    var reImage: String = "http://192.168.177.67:4000/uploads/${image}"

                    Glide.with(binding.root)
                        .load(reImage)
                        .error(R.drawable.noimg)
                        .centerCrop()
                        .into(binding.ivPost)
                }
            }

        }
}