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

    fun radioButtonOnClick(view: View)
    {
        if(view.id == R.id.rb_one)
        {
            var tv = findViewById<TextView>(R.id.ShowScoreText);
            tv.text = "Radio Button One";
        }
        if(view.id == R.id.rb_two)
        {
            var tv = findViewById<TextView>(R.id.ShowScoreText);
            tv.text = "Radio Button Two";
        }
        if(view.id == R.id.rb_three)
        {
            var tv = findViewById<TextView>(R.id.ShowScoreText);
            tv.text = "Radio Button Three";
        }
        if(view.id == R.id.rb_four)
        {
            var tv = findViewById<TextView>(R.id.ShowScoreText);
            tv.text = "Radio Button Four";
        }
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


        var randNum = Random();

        var leftNum = randNum.nextInt(10);
        var rightNum = randNum.nextInt(10);


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