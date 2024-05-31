package com.jayb.msg

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, 0, systemBars.right, 0)
            insets
        }
        val sendCard:CardView=findViewById(R.id.sendCard)
        sendCard.setOnClickListener {
            val intent=Intent(applicationContext,PostMessage::class.java)
            startActivity(intent)
        }

        val viewCard:CardView=findViewById(R.id.viewCard)
        viewCard.setOnClickListener {
            val intent=Intent(applicationContext,ViewMessage::class.java)
            startActivity(intent)
        }

        val chatCard:CardView=findViewById(R.id.chatCard)
        chatCard.setOnClickListener {
            val intent=Intent(applicationContext,Chat::class.java)
            startActivity(intent)
        }

        val deleteCard:CardView=findViewById(R.id.deleteCard)
        deleteCard.setOnClickListener {
            val intent=Intent(applicationContext,Rchat::class.java)
            startActivity(intent)
        }
    }
}