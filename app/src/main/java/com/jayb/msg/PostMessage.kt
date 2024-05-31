package com.jayb.msg

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONObject

class PostMessage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_post_message)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, 0, systemBars.right, 0)
            insets
        }
        val nickName:EditText=findViewById(R.id.etNickName)
        val txtMessage:EditText=findViewById(R.id.etMessage)
        val sendMsg:Button=findViewById(R.id.sendBtn)
        sendMsg.setOnClickListener {
            val api="https://farasi.pythonanywhere.com/messages"
            val body=JSONObject()
            body.put("message",txtMessage.text.toString())
            body.put("nickname",nickName.text.toString())

            val helper=ApiHelper(applicationContext)
            helper.post(api,body)
        }
    }
}