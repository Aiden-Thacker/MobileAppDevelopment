package com.example.bignumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Random

class MainActivity : AppCompatActivity() {

    private var leftNum :Int = 0;
    private var rightNum :Int = 0;
    private var score :Int = 0;
    private lateinit var myAdapter : ArrayAdapter<String>;
    private var data_def_list = ArrayList<String>();
    //var scoreText = findViewById<TextView>(R.id.ShowScoreText);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pickRandomNumber();
        setUpList();

        val def_list = findViewById<ListView>(R.id.dynamic_def_list);
        def_list.setOnItemClickListener { _, _, index, _ ->
            data_def_list.removeAt(index);
            myAdapter.notifyDataSetChanged();
        };
    }

    fun setUpList()
    {
        data_def_list.add("Three");
        data_def_list.add("Different");
        data_def_list.add("Words");

        myAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data_def_list);

        val def_list = findViewById<ListView>(R.id.dynamic_def_list);
        def_list.adapter = myAdapter;
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