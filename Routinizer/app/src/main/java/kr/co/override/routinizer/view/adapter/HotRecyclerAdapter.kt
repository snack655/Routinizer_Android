package kr.co.override.routinizer.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.co.override.routinizer.R
import kr.co.override.routinizer.databinding.ItemPostRecyclerBinding
import kr.co.override.routinizer.network.dapi.post

class HotRecyclerAdapter(val lifecycleOwner: LifecycleOwner) :
    RecyclerView.Adapter<HotRecyclerAdapter.HotViewHolder>() {

    var postList: List<post> = ArrayList<post>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HotViewHolder {
        return HotViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_post_recycler,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HotViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int = postList.size

    class HotViewHolder(private val binding: ItemPostRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(ppost: post){
                with(ppost) {
                    binding.tvCount.text = count.toString()
                    binding.tvTag.text = tag
                    binding.tvTitle.text = title
                    Glide.with(binding.root)
                        .load(img)
                        .error(R.drawable.noimg)
                        .centerCrop()
                        .into(binding.ivPost)
                }
            }

        }
}