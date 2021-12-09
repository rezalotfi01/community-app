package com.reza.community.screens.members

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.reza.community.R
import com.reza.community.databinding.ItemMemberBinding
import com.reza.presentation.model.MemberUiModel
import java.util.*

class MembersAdapter : PagingDataAdapter<MemberUiModel, MemberViewHolder>(MemberDiffCallBack()) {

    var onItemLiked: (MemberUiModel) -> Unit = {}
    var onItemUnliked: (MemberUiModel) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        return MemberViewHolder(
            ItemMemberBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        holder.bind(getItem(position), onItemLiked, onItemUnliked)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear(lifecycle: Lifecycle) {
        submitData(lifecycle, PagingData.empty())
        notifyDataSetChanged()
    }
}

class MemberDiffCallBack : DiffUtil.ItemCallback<MemberUiModel>() {
    override fun areItemsTheSame(oldItem: MemberUiModel, newItem: MemberUiModel): Boolean {
        return oldItem.uniqueField == newItem.uniqueField
    }

    override fun areContentsTheSame(oldItem: MemberUiModel, newItem: MemberUiModel): Boolean {
        return oldItem == newItem
    }
}


class MemberViewHolder(private val binding: ItemMemberBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(model: MemberUiModel?, onItemLiked: (MemberUiModel) -> Unit, onItemUnliked: (MemberUiModel) -> Unit) {
        if (model == null)
            return

        Glide.with(binding.root)
            .load(model.pictureUrl)
            .into(binding.imageViewAvatar)

        with(binding) {
            txtName.text = model.firstName
            txtDescription.text = model.topic

            cardContainer.setOnClickListener {
                btnLike.setImageResource(R.drawable.ic_like_selected)
                onItemLiked.invoke(model)
            }
            btnLike.setOnClickListener {
                btnLike.setImageResource(R.drawable.ic_like_normal)
                onItemUnliked.invoke(model)
            }
            if (model.isLiked)
                btnLike.setImageResource(R.drawable.ic_like_selected)
            else
                btnLike.setImageResource(R.drawable.ic_like_normal)
        }

        initReferenceCount(model)
        initNativesCount(model)
        initLearnsCount(model)
    }

    private fun initReferenceCount(model: MemberUiModel) {
        with(binding) {
            if (model.referenceCnt > 0) {
                txtReferenceCount.isVisible = true
                txtNewBadge.isVisible = false
                txtReferenceCount.text = model.referenceCnt.toString()
            } else {
                txtReferenceCount.isVisible = false
                txtNewBadge.isVisible = true
            }
        }
    }

    private fun initNativesCount(model: MemberUiModel) {
        var nativesStr = model.natives[0].uppercase(Locale.ROOT)
        if (model.natives.size > 1)
            nativesStr = nativesStr + " +" + (model.natives.size - 1).toString()
        binding.txtNatives.text = nativesStr
    }

    private fun initLearnsCount(model: MemberUiModel) {
        var learnsStr = model.learns[0].uppercase(Locale.ROOT)
        if (model.learns.size > 1)
            learnsStr = learnsStr + " +" + (model.learns.size - 1).toString()
        binding.txtLearns.text = learnsStr
    }



}
