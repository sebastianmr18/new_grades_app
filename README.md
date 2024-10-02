# 📱 navigation_example_app

> Esta es una aplicación sencilla donde se prueba funcionalidades de Kotlin tales como Navigation, Dialog y CardView.

EL primer paso es agregar las siguientes dependencias en el build.gradel.kts de la aplicación.
```bash
dependencies {
    val navVersion = "2.7.3"
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation ("androidx.navigation:navigation-ui-ktx:$navVersion")
    implementation("androidx.navigation:navigation-common:$navVersion")

    //cardView
    implementation("androidx.cardview:cardview:1.0.0")
}
```


---

## Instrucciones

A continuación, se explica como implementar las distintas funcionalidades.

### Navigation:
Esta funcionalidad permite navegar entre fragmentos dentro de una misma actividad con facilidad. Supongamos que tenemos dos fragmentos 'fragmentA' Y 'fragmentB', y queremos pasar de A a B.

A continuación los archivos de los Fragment.
```bash
<!--FragmentA.kt-->
package com.example.class_6.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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


    private fun navigationFragmentB() { /***/}
}
```
```bash
<!--fragment_a.xml-->
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".fragment.FragmentA">
....

        <Button
            android:id="@+id/navigateButton"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_marginStart="16dp"
            android:layout_marginEnd="16dp"
            android:layout_marginTop="40dp"
            android:text="Enviar datos"
            app:layout_constraintTop_toBottomOf="@id/inputLayoutGrade2"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            />

    </androidx.constraintlayout.widget.ConstraintLayout>

</layout>
```
```bash
<!--FragmentB.kt-->
package com.example.class_6.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.class_6.databinding.FragmentBBinding

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
....
    }
}
```
```bash
<!--fragment_b.xml-->
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    >

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".fragment.FragmentB">

      ....

    </androidx.constraintlayout.widget.ConstraintLayout>

</layout>
```
Luego, se crea una carpeta navigation, donde se añade el archivo nav_graph.xml. Este archivo permite visualizar y modificar las rutas de la aplicación. Aqui añadimos los fragment que hemos creado así.
> IMPORTANTE:
* Agregar el startDestination, que indiicara desde que fragment arranca la aplicación.
```bash
<!--nav_graph.xml-_>
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/nav_graph"
    app:startDestination="@id/fragmentA">

    <fragment
        android:id="@+id/fragmentA"
        android:name="com.example.class_6.fragment.FragmentA"
        android:label="FragmentA"
        tools:layout="@layout/fragment_a"
        >
    </fragment>
    <fragment
        android:id="@+id/fragmentB"
        android:name="com.example.class_6.fragment.FragmentB"
        android:label="FragmentB"
        tools:layout="@layout/fragment_b"
        >

    </fragment>

</navigation>
```
![Asì debe verse el codigo y el visualizador](https://github.com/user-attachments/assets/50fb0726-2943-43b1-ac80-9f321f726dcc)

Luego, se agrega la acción de pasar de un fragmento, así:
```bash
<!--nav_graph.xml-_>
<...>
  <...>
    <fragment
        android:id="@+id/fragmentA"
        android:name="com.example.class_6.fragment.FragmentA"
        android:label="FragmentA"
        tools:layout="@layout/fragment_a"
        >
        <action
            android:id="@+id/action_fragmentA_to_fragmentB"
            app:destination="@+id/fragmentB"
            />
    </fragment>
    <fragment
        android:id="@+id/fragmentB"
        android:name="com.example.class_6.fragment.FragmentB"
        android:label="FragmentB"
        tools:layout="@layout/fragment_b"
        >
    </fragment>
  </...>
</...>
```
Luego, en el fragmento original (el A), se le debe asignar la acción a un botón, en este caso se le asigna a navigateButton qu eya esta creado en el xml:
```bash
<!--FragmentA.kt-_>
package com.example.class_6.fragment

imports...

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


    private fun navigationFragmentB() {
        binding.navigateButton.setOnClickListener{
            ...
            findNavController().navigate(R.id.action_fragmentA_to_fragmentB, bundle)
        }
    }
}
```
![Ahora el visualizador debe verse así, notese la flecha que comunica a ambos fragmentos.](https://github.com/user-attachments/assets/1130d8be-a06c-430c-ac05-95307774a318)
### Pero ¿Como paso información de un fragment a otro?:
Para ello, debemos modificar la logica de los fragment, primero vamos con FragmentA, donde tenemos la funciòn navigationFragmentB, primero extraemos la data de los editText, creamos un objeto Bundle() y le asignamos una 'key' a cada una de las variables que vamos a pasarle al otro fragment:
```bash
<!--FragmentA.kt-_>
package com.example.class_6.fragment

imports...

class FragmentA : Fragment() {

...
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
```
Es decir, va a obtener los datos de los siguientes campos:

<p align="center">
    <img src="https://github.com/user-attachments/assets/b4a7e458-556a-4263-9dca-5f41ca27d5db" alt="FragmentA con sus campos de texto llenos" width="300" height="500">
<p/>

Ahora, debemos crear la función que va a recibir estos datos en el otro fragment, donde a un textView en el fragment destino se le asigna lo almacenado gracias a la 'key' del paso anterior:
```bash
<!--FragmentB.kt-->
package com.example.class_6.fragment

imports ....

class FragmentB : Fragment() {

    ...

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
}
```
Ahora los datos se muestran así en el Fragment B.

<p align="center">
    <img src="https://github.com/user-attachments/assets/9fba942e-ae51-4146-82a1-73d046323bd8" alt="FragmentB con los datos del FragmentA" width="300" height="500">
<p/>

### ¿Como mostrar un dialogo?
A continuación vamos a ver como se muestra un dialogo estandar, ademàs estudiaremos como pasarle valores como parametros y mostrarlos junto al dialogo.

En una carpeta, en este caso con el nombre 'dialogues', creamos un archivo llamado 'StandardDialogue.kt'
```bash
<!--StandardDialogue.kt-->
package com.example.class_6.dialogues

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class StandardDialogue {
    companion object{
        fun showDialog(context: Context, name: String, mean: Double): AlertDialog {
            val builder = AlertDialog.Builder(context) //Esta línea construye el dialogo
            builder.setCancelable(false) //Esta línea evita que se cierre el dialogo tocando fuera de su espacio
            builder.setTitle("Nota final de $name")
                .setMessage("TU nota es: $mean") //Estas líneas setean el titulo y contenido del dialogo
                .setPositiveButton("Feliz") { //Aqui se muestran las acciones del boton positivo
                    dialog, _ ->
                    Toast.makeText(context, "FELIIIIZ", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                .setNegativeButton("Tristeee") { //Aqui se muestran las acciones del boton negativo
                    dialog, _ ->
                    Toast.makeText(context, "TROSTE :(", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                } //dialog.dismiss() cierra el dialog
            return builder.create()
        }
    }
}
```
Supongamos que el dialogo lo vamos a mostrar desde el fragmentB, entonces en su logica agregamos estas funciones.
```bash
<!--FragmentB.kt-->
package com.example.class_6.fragment

imports ....

class FragmentB : Fragment() {

    ...

    private fun captureData(){
        ....
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
```
El dialogo se debe ver asì:

<p align="center">
    <img src="https://github.com/user-attachments/assets/6afe6f13-c2f7-4e07-908b-183bd7ef6465" alt="Dialogo con los datos" width="300" height="500">
<p/>
