package com.example.a2in1app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var guess=findViewById<Button>(R.id.wordgame)
        var num=findViewById<Button>(R.id.numgame)
        guess.setOnClickListener{
            val intent = Intent(this, guessgame::class.java)
            startActivity(intent)
        }
        num.setOnClickListener{
            val intent = Intent(this, numbergame::class.java)
            startActivity(intent)
        }


    }


    /////////////////////menu////////////////////////////


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {

        val item1 = menu!!.getItem(0)
        val item2 = menu!!.getItem(1)
        val item3 = menu!!.getItem(2)
        item1.setTitle("Numbers Game")
        item2.setTitle("Guess The Phrase")
        item3.isVisible=false

        return super.onPrepareOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.m1 -> {
                val intent = Intent(this, numbergame::class.java)
                startActivity(intent)
                return true
            }
            R.id.m2 -> {
                val intent = Intent(this, guessgame::class.java)
                startActivity(intent)
                return true
            }
            R.id.m3 -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }
}