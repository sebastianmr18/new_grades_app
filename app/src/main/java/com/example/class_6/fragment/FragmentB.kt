package com.example.class_6.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.class_6.R
import com.example.class_6.databinding.FragmentBBinding
import com.example.class_6.dialogues.StandardDialogue.Companion.showDialog


class FragmentB : Fragment() {

    private lateinit var binding: FragmentBBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        captureData()
    }

    private fun captureData(){
        val textNameView = binding.textNameFragmentB
        val textNameFragmentA = arguments?.getString("keyName")
        textNameView.text = "Nombre: $textNameFragmentA"

        val textGrade1View = binding.grade1FragmentB
        val textGrade1FragmentA = arguments?.getString("keyGrade1")
        textGrade1View.text = "Nota 1: $textGrade1FragmentA"

        val textGrade2View = binding.grade2FragmentB
        val textGrade2FragmentA = arguments?.getString("keyGrade2")
        textGrade2View.text = "Nota 2: $textGrade2FragmentA"

        val data = arrayOf(
            textNameFragmentA ?: "Nombre no disponible", // Si no hay nombre, se pone un texto por defecto
            textGrade1FragmentA ?: "0.0",                 // Si la nota es nula, se pone "0"
            textGrade2FragmentA ?: "0.0"                  // Si la nota es nula, se pone "0"
        )

        calculateGrade(data)

    }

    private fun calculateGrade(data: Array<String>){
        val name = data[0]
        val grade1 = data[1].toDoubleOrNull() ?: 0.0
        val grade2 = data[2].toDoubleOrNull() ?: 0.0

        val mean = (grade1 + grade2) / 2

        showStandardDialogue(name, mean)
    }

    private fun showStandardDialogue(name: String, mean: Double) {
        binding.calculateButton.setOnClickListener{
            showDialog(binding.root.context, name, mean).show()
        }
    }
}