package com.example.bignumber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Random
import java.util.Scanner

data class WordDefinition(var word: String, val definition: String);

class MainActivity : AppCompatActivity() {
    private val ADD_WORD_CODE: Int = 1234; //1-65535
    private var score :Int = 0;
    private var totalCorrect :Int = 0;
    private var totalWrong :Int = 0;
    private lateinit var myAdapter : ArrayAdapter<String>;
    private var data_def_list = ArrayList<String>();
    private var wordDefinitions = mutableListOf<WordDefinition>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("Activity Watch", "onCreate")

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
                totalCorrect++;
            }
            else
            {
                score--;
                totalWrong++;
            }
            findViewById<TextView>(R.id.scoreText).text = "Score: $score";
            data_def_list.removeAt(index);
            myAdapter.notifyDataSetChanged();
            refreshWordAndDefinition();
        };
    }

    override fun onStart()
    {
        super.onStart();

        Log.d("Activity Watch", "onStart");
    }

    override fun onResume()
    {
        super.onResume();

        Log.d("Activity Watch", "onResume");
    }

    override fun onDestroy()
    {
        super.onDestroy()

        Log.d("Activity Watch", "onDestroy")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == ADD_WORD_CODE)
        {
            if(data != null)
            {
                val word = data.getStringExtra("word")?:""
                val def = data.getStringExtra("def")?:""

                wordDefinitions.add(WordDefinition(word, def));
                myAdapter.notifyDataSetChanged();
                refreshWordAndDefinition();
            }
        }
    }

    private fun loadPlayerData()
    {
        val reader = Scanner(resources.openRawResource(R.raw.game_data));
        while(reader.hasNextLine())
        {
            val line = reader.nextLine();
            val wd = line.split("|");
            wordDefinitions.add(WordDefinition(wd[0],wd[1]));
        }
    }

    private fun savePlayerData()
    {

    }

    fun statsOnClick(view: View)
    {
        val myIntent = Intent(this, StatsActivity::class.java);
        myIntent.putExtra("score", score.toString());
        myIntent.putExtra("totalCorrect", totalCorrect.toString());
        myIntent.putExtra("totalWrong", totalWrong.toString());
        startActivity(myIntent);
    }
    fun addWordOnClick(view: View)
    {
        val myIntent = Intent(this, AddWordActivity::class.java);
        startActivityForResult(myIntent, ADD_WORD_CODE);
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
        loadPlayerData();
        /*
        wordDefinitions.add(WordDefinition("simple","easy to understand"));
        wordDefinitions.add(WordDefinition("kotlin","programming language"));
        wordDefinitions.add(WordDefinition("ui","user interface"));
        wordDefinitions.add(WordDefinition("ux","user experience"));
        */
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