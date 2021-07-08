package com.example.infinitescroll

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.infinitescroll.databinding.ItemLoadingBinding
import com.example.infinitescroll.databinding.ItemNoticeBinding

class NoticeAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1
    private val items = ArrayList<Content>()

    inner class NoticeViewHolder(private val binding: ItemNoticeBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(notice: Content){
            binding.tvTitle.text = notice.title
            binding.tvDate.text = notice.created.substring(0,10)
        }
    }

    inner class LoadingViewHolder(private val binding: ItemLoadingBinding) : RecyclerView.ViewHolder(binding.root){

    }

    // 뷰의 타입을 결정해주는 곳.
    override fun getItemViewType(position: Int): Int {
        return when(items[position].title){
            " " -> VIEW_TYPE_LOADING
            else -> VIEW_TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            VIEW_TYPE_ITEM -> {
                val binding = ItemNoticeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                NoticeViewHolder(binding)
            }
            else -> {
                val binding = ItemLoadingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                LoadingViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NoticeViewHolder){
            holder.bind(items[position])
        }
        else{

        }
    }

    fun setList(notice: MutableList<Content>){
        items.addAll(notice)
        items.add(Content(" "," ")) // progress bar 넣을 자리
    }

    fun deleteLoading(){
        items.removeAt(items.lastIndex)
    }
}