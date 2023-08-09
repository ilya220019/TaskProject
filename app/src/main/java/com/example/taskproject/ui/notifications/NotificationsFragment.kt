package com.example.taskproject.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.taskproject.Model.Car
import com.example.taskproject.databinding.FragmentNotificationsBinding
import com.example.taskproject.ui.notifications.adapter.CarAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val db: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val adapter = CarAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rv.adapter = adapter
        db.collection(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .get().addOnSuccessListener {
                val data = it.toObjects(Car::class.java)
                adapter.addCars(data)
            }.addOnFailureListener {
                Toast.makeText(requireContext(), "Что-то не так", Toast.LENGTH_SHORT)

            }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}