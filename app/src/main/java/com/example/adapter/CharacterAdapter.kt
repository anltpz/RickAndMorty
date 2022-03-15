package com.example.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.RvRowDesingBinding
import com.example.rickandmorty.model.Results

class CharacterAdapter : PagingDataAdapter<Results, CharacterAdapter.MyHolder>(DiffUtilClass()) {


    private lateinit var onClick: (Results) -> Unit


    fun setResulsClickListener(onClick: (Results) -> Unit) {
        this.onClick = onClick;
    }

    inner class MyHolder(
        private val binding: RvRowDesingBinding,
        private val resultClick: (Results) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(results: Results) {
            binding.result = results
            binding.executePendingBindings()
            itemView.setOnClickListener {
                resultClick(results)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder =
        MyHolder(
            RvRowDesingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), resultClick = onClick
        )


    override fun onBindViewHolder(holder: CharacterAdapter.MyHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }

    }


    class DiffUtilClass() : DiffUtil.ItemCallback<Results>() {
        override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem == newItem
        }

    }


}