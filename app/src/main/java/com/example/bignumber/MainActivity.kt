package com.example.bignumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.Random

class MainActivity : AppCompatActivity() {

    private var leftNum :Int = 0;
    private var rightNum :Int = 0;
    private var score :Int = 0;
    //var scoreText = findViewById<TextView>(R.id.ShowScoreText);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pickRandomNumber();
        //scoreText.setText(score);
    }

    fun leftButtonOnClick(view: View)
    {
        pickRandomNumber();
        //increaseScoreCounterIfLeftClicked();
    }

    fun rightButtonOnClick(view: View)
    {
        pickRandomNumber();
        //increaseScoreCounterIfRightClicked();
    }

    fun pickRandomNumber()
    {
        var leftButton = findViewById<Button>(R.id.left_number_button);
        var rightButton = findViewById<Button>(R.id.right_number_button);

        var randNum = Random();

        var leftNum = randNum.nextInt(10);
        var rightNum = randNum.nextInt(10);

        leftButton.text = "$leftNum";
        rightButton.text = "$rightNum";
    }

    fun increaseScoreCounterIfLeftClicked()
    {

        if(leftNum >= rightNum)
        {
            score++;
            //scoreText.setText(score);
        }else
        {
            score--;
            //scoreText.setText(score);
        }
    }

    fun increaseScoreCounterIfRightClicked()
    {
        if(rightNum >= leftNum)
        {
            score++;
            //scoreText.setText(score);
        }else
        {
            score--;
            //scoreText.setText(score);
        }
    }
}