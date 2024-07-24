package com.example.musicapp.view.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicapp.databinding.SearchItemBinding
import com.example.musicapp.view.search.model.Item

class SearchAdapter(
    var context: Context,
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    var list: ArrayList<Item> = ArrayList()

    class SearchViewHolder(var binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SearchItemBinding.inflate(inflater, parent, false)
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.binding.txtTitle.setText(list[position].name)
        Glide.with(context).load(list[position].album!!.images?.get(1)!!.url)
            .into(holder.binding.image)
        val artistNames = list[position].artists!!.map { it.name }.joinToString(", ")
        holder.binding.txtArtist.setText(artistNames)
    }

    fun updateList(list: ArrayList<Item>) {
        this.list = list
        notifyDataSetChanged()
    }
}