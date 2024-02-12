package com.example.bignumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.util.Random

class MainActivity : AppCompatActivity() {

    private var leftNum :Int = 0;
    private var rightNum :Int = 0;
    private var score :Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pickRandomNumber();
    }

    fun leftButtonOnClick(view: View)
    {
        if(leftNum >= rightNum)
        {
            score++;
        }
        else
        {
            score--;
        }
        updateScoreText();
        pickRandomNumber();
    }

    fun rightButtonOnClick(view: View)
    {
        if(leftNum <= rightNum)
        {
            score++;
        }
        else
        {
            score--;
        }
        updateScoreText();
        pickRandomNumber();
    }

    private fun pickRandomNumber()
    {
        val leftButton = findViewById<Button>(R.id.left_number_button);
        val rightButton = findViewById<Button>(R.id.right_number_button);

        val randNum = Random();

        leftNum = randNum.nextInt(10);
        rightNum = randNum.nextInt(10);

        leftButton.text = "$leftNum";
        rightButton.text = "$rightNum";
    }

    private fun updateScoreText()
    {
        val scoreText = findViewById<TextView>(R.id.ShowScoreText);
        var winImage = findViewById<ImageView>(R.id.Win_image);
        val scoreString: String = scoreText.text.toString().drop(7);
        if(scoreString.toInt() > score)
        {
            Toast.makeText(this, "Score decreased try again", Toast.LENGTH_LONG).show();
        }
        else if (scoreString.toInt() < score)
        {
            Toast.makeText(this, "Score increased", Toast.LENGTH_SHORT).show();
        }

        if(score >= 5)
        {
            winImage.visibility = View.VISIBLE;
        }else
        {
            winImage.visibility = View.INVISIBLE;
        }

        scoreText.text = "Score: $score";
    }
}