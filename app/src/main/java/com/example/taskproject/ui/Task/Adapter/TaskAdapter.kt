package com.example.taskproject.ui.Task.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskproject.Model.Task
import com.example.taskproject.databinding.ItemTaskBinding

class TaskAdapter:RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private val list = arrayListOf<Task>()
    fun addTask(task: Task){
        list.add(0, task)
        notifyItemChanged(0)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(list[position])
    }
    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        ViewHolder(binding.root){
        fun bind(task: Task){
            binding.tvTitle.text  = task.title
            binding.tvDesc.text  = task.desc
        }
    }

}