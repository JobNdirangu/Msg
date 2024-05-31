package com.jayb.msg

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val registerEt:EditText=findViewById(R.id.registerEt)
        val registerBtn:Button=findViewById(R.id.registerBtn)
        registerBtn.setOnClickListener {

            val prefs=applicationContext.getSharedPreferences("storage", MODE_PRIVATE)
            val editor=prefs.edit()
            editor.putString("username",registerEt.text.toString())
            editor.apply()

            val regUser=prefs.getString("username","")
            Toast.makeText(applicationContext, "Welcome $regUser to WeLchat", Toast.LENGTH_SHORT).show()
            val api="https://farasi.pythonanywhere.com/messages"
            val body=JSONObject()
            body.put("username",registerEt.text.toString())
            body.put("message","Joined WeLchat")
            val helper=ApiHelper(applicationContext)
            helper.post(api,body)

            val hander=Handler()
            hander.postDelayed({
                startActivity(Intent(applicationContext,MainActivity::class.java))
            },6555)
        }
    }
}