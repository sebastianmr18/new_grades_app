package com.example.class_6.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.class_6.R
import com.example.class_6.databinding.FragmentABinding

class FragmentA : Fragment() {

    private lateinit var binding: FragmentABinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentABinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationFragmentB()
    }

    private fun cleanForm(){
        binding.editTextName.text=null
        binding.editTextGrade1.text=null
        binding.editTextGrade2.text=null
    }

    private fun navigationFragmentB() {
        binding.navigateButton.setOnClickListener{
            val dataName = binding.editTextName.text.toString()
            val dataGrade1 = binding.editTextGrade1.text.toString()
            val dataGrade2 = binding.editTextGrade2.text.toString()
            val bundle = Bundle()
            bundle.putString("keyName", dataName)
            bundle.putString("keyGrade1", dataGrade1)
            bundle.putString("keyGrade2", dataGrade2)
            findNavController().navigate(R.id.action_fragmentA_to_fragmentB, bundle)
        }
    }
}