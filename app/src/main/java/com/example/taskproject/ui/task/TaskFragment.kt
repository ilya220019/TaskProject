package com.example.taskproject.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.taskproject.App
import com.example.taskproject.Model.Task
import com.example.taskproject.R
import com.example.taskproject.databinding.FragmentTaskBinding
import com.example.taskproject.ui.home.HomeFragment.Companion.TASK_KEY

class TaskFragment : Fragment() {
    private lateinit var binding: FragmentTaskBinding
    private var task:Task?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)= with(binding) {
        super.onViewCreated(view, savedInstanceState)
        task = arguments?.getSerializable(TASK_KEY) as Task?
        if (task != null){
            etTitle.setText(task?.title.toString())
            etDesc.setText(task?.desc.toString())
            btnSave.setText(getString(R.string.update))
        }
        btnSave.setOnClickListener {
            if (etTitle.text.isNullOrEmpty()) {
                etTitle.setError("This field can not be blank")
            } else{
           if (task==null){
               save()
           }else{
               update()
           }
            findNavController().navigateUp()
        }}
    }
    private fun update()= with(binding){
       val data = task?.copy(
            title = etTitle.text.toString(),
            desc = etDesc.text.toString()
        )
        if (data != null) {
            App.db.taskDao().update(data)
        }
    }
    private fun save()= with(binding){
        val data = Task(
                title = etTitle.text.toString(),
                desc  = etDesc.text.toString())
            App.db.taskDao().insert(data)

    }
}