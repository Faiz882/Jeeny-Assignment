package com.assignment.jeeny.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.jeeny.model.GithubRepoModel
import com.example.jeeny.databinding.ItemGithubRepoBinding

class GithubRepoAdapter : RecyclerView.Adapter<GithubRepoAdapter.VH>() {
    private var list: List<GithubRepoModel> = emptyList()

    inner class VH(private val binding: ItemGithubRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GithubRepoModel) {
            binding.repo = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemGithubRepoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun submitList(items: List<GithubRepoModel>) {
        list = items
        notifyDataSetChanged()
    }
}