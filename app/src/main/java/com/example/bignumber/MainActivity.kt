package com.example.bignumber

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.gridlayout.widget.GridLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Collections





class MainActivity : AppCompatActivity() {

    private lateinit var gridLayout: GridLayout
    private lateinit var radioButtons: MutableList<RadioButton>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridLayout = findViewById(R.id.Grid_layout)
        radioButtons = mutableListOf<RadioButton>().apply {
            add(findViewById(R.id.rb_pokemon_one))
            add(findViewById(R.id.rb_pokemon_two))
            add(findViewById(R.id.rb_pokemon_three))
            add(findViewById(R.id.rb_pokemon_four))
        }

        pickRandomOrder();

    }

    fun radioButtonOnClick(view: android.view.View)
    {
        val clickedButton = view as RadioButton
        radioButtons.filter { it != clickedButton }.forEach { it.isChecked = false }
    }

    private fun pickRandomOrder()
    {
        try {
            radioButtons.shuffle()

            gridLayout.removeAllViews()

            radioButtons.forEach { radioButton ->
                gridLayout.addView(radioButton)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}