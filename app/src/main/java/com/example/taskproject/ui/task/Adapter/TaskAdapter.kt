package com.example.taskproject.ui.task.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskproject.Model.Task
import com.example.taskproject.databinding.ItemTaskBinding

class TaskAdapter(val longClickItem: (task: Task)-> Unit, val onClick:(task: Task) -> Unit) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private val list = arrayListOf<Task>()
    fun addTask(task: Task) {
        list.add(0, task)
        notifyItemChanged(0)
    }

    fun addTasks(tasks: List<Task>) {
        list.clear()
        list.addAll(tasks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.changeColor()
            binding.tvTitle.text = task.title
            binding.tvDesc.text = task.desc
            itemView.setOnLongClickListener {
                longClickItem(task)
                true
            }
            binding.root.setOnClickListener {
                onClick(task)
            }
        }

        private fun ItemTaskBinding.changeColor() {

            if (adapterPosition % 2 == 0){
                itemCon.setBackgroundColor(Color.BLACK)
                tvTitle.setTextColor(Color.WHITE)
                tvDesc.setTextColor(Color.WHITE)
            } else {
                itemCon.setBackgroundColor(Color.WHITE)
                tvTitle.setTextColor(Color.BLACK)
                tvDesc.setTextColor(Color.BLACK)
            }        }

    }


}