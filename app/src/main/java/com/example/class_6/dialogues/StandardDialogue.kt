package com.example.class_6.dialogues

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class StandardDialogue {
    companion object{
        fun showDialog(context: Context, name: String, mean: Double): AlertDialog {
            val builder = AlertDialog.Builder(context)
            builder.setCancelable(false)
            builder.setTitle("Nota final de $name")
                .setMessage("TU nota es: $mean")
                .setPositiveButton("Feliz") {
                    dialog, _ ->
                    Toast.makeText(context, "FELIIIIZ", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                .setNegativeButton("Tristeee") {
                    dialog, _ ->
                    Toast.makeText(context, "TROSTE :(", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
            return builder.create()
        }
    }
}