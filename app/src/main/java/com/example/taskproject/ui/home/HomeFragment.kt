package com.example.taskproject.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.taskproject.App
import com.example.taskproject.Model.Task
import com.example.taskproject.R
import com.example.taskproject.databinding.FragmentHomeBinding
import com.example.taskproject.ui.Task.Adapter.TaskAdapter
import com.example.taskproject.ui.Task.TaskFragment.Companion.RESULT_KEY
import com.example.taskproject.ui.Task.TaskFragment.Companion.RESULT_REQUES_KEY

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val adapter = TaskAdapter(this::onClickItem)

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
        val data = App.db.taskDao().getAll()
        adapter.addTasks(data)
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }
    private fun onClickItem(task: Task){
        showAlertDialog(task)
    }
    private fun showAlertDialog(task: Task) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Удалить").setMessage("Вы точно хотите удалить?").setCancelable(true)
            .setPositiveButton("Да") { dialog, which ->
                App.db.taskDao().delete(task)
                val tasks = App.db.taskDao().getAll()
                adapter.addTasks(tasks)
            }.setNegativeButton("Нет") { dialog, which -> }.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}