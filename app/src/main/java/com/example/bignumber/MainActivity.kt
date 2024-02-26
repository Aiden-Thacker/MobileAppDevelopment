package com.example.bignumber

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.util.Collections


class MainActivity : AppCompatActivity() {

    private lateinit var gridLayout: GridLayout
    private lateinit var radioButtons: MutableList<RadioButton>

    private lateinit var imageView: ImageView
    private val imageResourceIds = listOf(
        R.drawable.whosthatpokemonclefairysilhouette,
        R.drawable.whosthatpokemongastlysilhouette,
        R.drawable.whosthatpokemonninetalessilhouette,
        R.drawable.whosthatpokemonpidgeottosilhouette
    )

    private var score :Int = 0;

    @SuppressLint("MissingInflatedId")
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

        imageView = findViewById<ImageView>(R.id.PokemonImage);

        //pickRandomOrder();
        displayRandomImage();

    }

    fun radioButtonOnClick(view: View)
    {
        val clickedButton = view as RadioButton;
        radioButtons.filter { it != clickedButton }.forEach { it.isChecked = false };
    }

    private fun pickRandomOrder()
    {
        try
        {
            radioButtons.shuffle();

            gridLayout.removeAllViews();

            radioButtons.forEach { radioButton ->
                gridLayout.addView(radioButton)
            }
        }
        catch (e: Exception)
        {
            e.printStackTrace()
        }
    }

    private fun displayRandomImage()
    {
        val randomImageResourceId = imageResourceIds.random()
        val drawable = ContextCompat.getDrawable(this, randomImageResourceId)
        imageView.setImageDrawable(drawable)

        imageView.tag = randomImageResourceId
    }

    fun onButtonClick(view: View)
    {
        val currentImageResourceId = imageView.tag as? Int ?: -1

        val selectedImageResourceId = when (radioButtons.find { it.isChecked }?.id) {
            R.id.rb_pokemon_one -> R.drawable.whosthatpokemonninetalessilhouette
            R.id.rb_pokemon_two -> R.drawable.whosthatpokemongastlysilhouette
            R.id.rb_pokemon_three -> R.drawable.whosthatpokemonpidgeottosilhouette
            R.id.rb_pokemon_four -> R.drawable.whosthatpokemonclefairysilhouette
            else -> -1 // Use a default value if the radio button ID is not found
        }

        if (currentImageResourceId == selectedImageResourceId) {
            //Toast.makeText(this, "Matched", LENGTH_SHORT).show();
            score++;
        } else {
            //Toast.makeText(this, "Did not Match", LENGTH_SHORT).show();
            score--;
        }

        updateScore();
        updateImageToFilledInVersion();

        val submitButton = findViewById<Button>(R.id.submit_button);
        submitButton.visibility = View.INVISIBLE;

        val nextButton = findViewById<Button>(R.id.next_button);
        nextButton.visibility = View.VISIBLE;
    }

    private fun updateScore()
    {
        var scoreText = findViewById<TextView>(R.id.ShowScoreText);

        scoreText.text = "Score: $score";
    }

    private fun updateImageToFilledInVersion() {
        val currentImageResourceId = imageView.tag as Int;

        val filledInImageResourceId = when (currentImageResourceId) {
            R.drawable.whosthatpokemonclefairysilhouette -> R.drawable.whosthatpokemonclefairyfilled
            R.drawable.whosthatpokemongastlysilhouette -> R.drawable.whosthatpokemongastlyfilled
            R.drawable.whosthatpokemonninetalessilhouette -> R.drawable.whosthatpokemonninetalesfilled
            R.drawable.whosthatpokemonpidgeottosilhouette -> R.drawable.whosthatpokemonpidgeottofilled
            else -> -1
        }

        imageView.setImageResource(filledInImageResourceId);
    }

    fun nextButtonClick(view: View)
    {
        val nextButton = findViewById<Button>(R.id.next_button);
        nextButton.visibility = View.INVISIBLE;

        val submitButton = findViewById<Button>(R.id.submit_button);
        submitButton.visibility = View.VISIBLE;

        radioButtons.forEach { radioButton ->
            radioButton.isChecked = false
        }

        displayRandomImage();
    }



}


