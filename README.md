# 📱 navigation_example_app

> Esta es una aplicación sencilla donde se prueba funcionalidades de Kotlin tales como Navigation, Dialog y CardView.

> Se deben agregar las siguientes dependencias en el build.gradel.kts de la aplicación.
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
Esta funcionalidad permite navegar entre fragmentos dentro de una misma actividad con facilidad. Supongamos que tenemos dos fragmentos 'fragmentA' Y 'fragmentB', y queremos pasar de A a B.\n
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
![Asì debe verse el codigo y el visualizador](https://drive.google.com/file/d/1zK1iberDQT_XFQW-Hn9E5FCLr1uj4gqv/view?usp=drive_link)

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
![Ahora el visualizador debe verse así, notese la flecha que comunica a ambos fragmentos.](https://imgur.com/a/O1LIre5.png)
