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

data class WordDefinition(var word: String, val definition: String);

class MainActivity : AppCompatActivity() {

    private var leftNum :Int = 0;
    private var rightNum :Int = 0;
    private var score :Int = 0;
    private lateinit var myAdapter : ArrayAdapter<String>;
    private var data_def_list = ArrayList<String>();
    private var wordDefinitions = mutableListOf<WordDefinition>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadDefinition();
        setUpList();


        val def_list = findViewById<ListView>(R.id.dynamic_def_list);
        def_list.setOnItemClickListener { _, _, index, _ ->
            var correctDef: String = ""
            for(wd in wordDefinitions)
            {
                if(wd.word == findViewById<TextView>(R.id.defineWord).text.toString())
                {
                    correctDef = wd.definition;
                }
            }

            if(correctDef == data_def_list[index])
            {
                score++;
            }
            else
            {
                score--;
            }
            findViewById<TextView>(R.id.scoreText).text = "Score: $score";
            data_def_list.removeAt(index);
            myAdapter.notifyDataSetChanged();
            refreshWordAndDefinition();
        };
    }

    fun setUpList()
    {
        refreshWordAndDefinition();

        myAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data_def_list);

        val def_list = findViewById<ListView>(R.id.dynamic_def_list);
        def_list.adapter = myAdapter;
    }

    fun loadDefinition()
    {
        wordDefinitions.add(WordDefinition("simple","easy to understand"));
        wordDefinitions.add(WordDefinition("kotlin","programming language"));
        wordDefinitions.add(WordDefinition("ui","user interface"));
        wordDefinitions.add(WordDefinition("ux","user experience"));
    }

    fun refreshWordAndDefinition()
    {
        var rand = Random();
        findViewById<TextView>(R.id.defineWord).text = wordDefinitions[rand.nextInt(wordDefinitions.size)].word;

        data_def_list.clear();

        for(wd in wordDefinitions)
        {
            data_def_list.add(wd.definition);
        }

        data_def_list.shuffle();
    }

    /*fun checkWordAndDefinition()
    {
        var correctDef: String = ""
        for(wd in wordDefinitions)
        {
            if(wd.word == findViewById<TextView>(R.id.defineWord).text.toString())
            {
                correctDef = wd.definition;
            }
        }

        if(correctDef == data_def_list[index])
        {
            score++;
        }
        else
        {
            score--;
        }
        findViewById<TextView>(R.id.scoreText).text = "Score: $score";
    }*/
}