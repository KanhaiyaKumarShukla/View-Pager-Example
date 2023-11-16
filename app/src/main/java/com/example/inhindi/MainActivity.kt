package com.example.inhindi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tvAnimal: TextView=findViewById(R.id.tvAnimal);
        val tvCity: TextView=findViewById(R.id.tvCity)
        val tvFruit: TextView=findViewById(R.id.tvFruit)
        val tvFlower: TextView=findViewById(R.id.tvFlower)
        tvAnimal.setOnClickListener{
            val intent=Intent(this, animal::class.java)
            startActivity(intent)
        }
        tvFruit.setOnClickListener{
            val intent=Intent(this, fruit::class.java)
            startActivity(intent)
        }
        tvCity.setOnClickListener {
            val intent=Intent(this, city::class.java)
            startActivity(intent)
        }
        tvFlower.setOnClickListener{
            val intent=Intent(this, flower::class.java)
            startActivity(intent)
        }
    }
}