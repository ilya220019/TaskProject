package com.example.taskproject.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskproject.Model.OnBoarding
import com.example.taskproject.databinding.ItemOnboardingBinding

class OnBoardingAdapter() :Adapter<OnBoardingAdapter.OnBoardingViewHolder>()
{
    private val data = arrayListOf(
        OnBoarding("Test 1", "Desc 1", ""),
        OnBoarding("Test 2", "Desc 2", ""),
        OnBoarding("Test 3", "Desc 3", "")
            )
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(ItemOnboardingBinding.inflate(LayoutInflater.from(parent.context), parent,false)) }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(data.get(position))
    }
    inner class OnBoardingViewHolder(private val binding: ItemOnboardingBinding): ViewHolder(binding.root){
        fun bind(onBoarding: OnBoarding){
            binding.tvTitle.text = onBoarding.title
            binding.tvDesc.text = onBoarding.desc
            binding.btnStart.isVisible = adapterPosition == data.lastIndex
            binding.skip.isVisible = adapterPosition != data.lastIndex
        }
    }

}