package com.example.taskproject.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.taskproject.Model.Task
import com.example.taskproject.R
import com.example.taskproject.databinding.FragmentHomeBinding
import com.example.taskproject.ui.Task.Adapter.TaskAdapter
import com.example.taskproject.ui.Task.TaskFragment.Companion.RESULT_KEY
import com.example.taskproject.ui.Task.TaskFragment.Companion.RESULT_REQUES_KEY

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val adapter = TaskAdapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rv.adapter = adapter
        setFragmentResultListener(RESULT_REQUES_KEY) { _, bundle ->
            val data = bundle.getSerializable(RESULT_KEY) as Task
            adapter.addTask(data)
        }
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}