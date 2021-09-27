package com.example.a2in1app

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.system.exitProcess

class numbergame : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numbergame)


        var input = ArrayList<String>()
        var quess =findViewById<Button>(R.id.button)
        var inin =findViewById<EditText>(R.id.input)
        val rv= findViewById<RecyclerView>(R.id.rvMain)
        var qgue=(0..11).random()
        var limit=3


        val dialogBuilder = AlertDialog.Builder(this)

        val dialogBuilderwin = AlertDialog.Builder(this)

        val dialogBuilderlose = AlertDialog.Builder(this)

        dialogBuilder.setMessage("Enter numbers ONLY")

            .setPositiveButton("i understand", DialogInterface.OnClickListener {
                    dialog, id -> null })

            .setNegativeButton("NO", DialogInterface.OnClickListener {
                    dialog, id -> exitProcess(0) })

        dialogBuilderwin.setMessage("Congrats you got the correct answer do you want to play again? ")
            .setPositiveButton("play again",DialogInterface.OnClickListener {
                    dialog, id ->  input.clear(); limit=3 ;qgue=(0..11).random(); rv.adapter = RVAdapter(input) })
            .setNegativeButton("NO",DialogInterface.OnClickListener{
                    dialog, id -> exitProcess(0)})

        dialogBuilderlose.setMessage("do you want to play again? ")
            .setPositiveButton("play again",DialogInterface.OnClickListener{
                    dialog, id ->  input.clear(); limit=3 ;qgue=(0..11).random(); rv.adapter = RVAdapter(input) })
            .setNegativeButton("NO",DialogInterface.OnClickListener{
                    dialog ,id -> exitProcess(0)})

        val nullinput = dialogBuilder.create()
        val wind=dialogBuilderwin.create()
        val lossd=dialogBuilderlose.create()

        nullinput.setTitle("wrong input")
        wind.setTitle("you win")
        lossd.setTitle("you lost")





        rv.adapter = RVAdapter(input)
        rv.layoutManager = LinearLayoutManager(this)


        quess.setOnClickListener{

            if(inin.text.toString().toIntOrNull()==null ){

                nullinput.show()
            }else{
                input.add("you guessed ${inin.text}")
                rv.adapter = RVAdapter(input)
                if(inin.text.toString().toInt()==qgue){
                    input.add("your guess is correct")
                    rv.adapter = RVAdapter(input)
                    wind.show()
                }else{
                    limit--
                    input.add("you have $limit guesses left")
                    rv.adapter = RVAdapter(input)
                    if (limit==0){
                        lossd.show()
                    }
                }
            }
            inin.text.clear()
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
        item1.setTitle("New Game")
        item2.setTitle("Guess the Phrase")
        item3.setTitle("Main Menu")

        return super.onPrepareOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.m1 -> {
                recreate()
                return true
            }
            R.id.m2 -> {
                val intent = Intent(this, guessgame::class.java)
                startActivity(intent)
                return true
            }
            R.id.m3 -> {
                val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}