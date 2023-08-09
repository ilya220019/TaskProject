package com.example.taskproject.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.taskproject.Model.Car
import com.example.taskproject.databinding.FragmentDashboardBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
   private val db: FirebaseFirestore by lazy {
       FirebaseFirestore.getInstance()
   }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            val data= Car(
                brand = binding.etMarka.text.toString(),
                model = binding.etModel.text.toString()
            )
            db.collection(FirebaseAuth.getInstance().currentUser?.uid.toString())
                .add(data).addOnSuccessListener {
                    binding.etMarka.text?.clear()
                    binding.etModel.text?.clear()
                }.addOnFailureListener {
                    Toast.makeText(requireContext(), "Что-то не так", Toast.LENGTH_SHORT)

                }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}