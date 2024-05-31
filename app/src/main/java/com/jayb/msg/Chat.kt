package com.jayb.msg

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONArray
import org.json.JSONObject

class Chat : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chat)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom)
            insets
        }

        val progessBar: ProgressBar =findViewById(R.id.progressBar)
        val api="https://farasi.pythonanywhere.com/messages"
        val helper=ApiHelper(applicationContext)
        helper.get(api,object :ApiHelper.CallBack{
            override fun onSuccess(result: String?) {
                val messageJSONArray= JSONArray(result.toString())
                (0 until messageJSONArray.length()).forEach(){
                    val message=messageJSONArray.getJSONObject(it)
                    val messagetv: TextView =findViewById(R.id.tvChat)
                    messagetv.append("Sender"+message.get("nickname")+"\n")
                    messagetv.append(""+message.get("message")+"\n")
                    messagetv.append("\n\n ")
                }//end of loop
                progessBar.visibility=View.GONE
            }
            override fun onFailure(error: Throwable?) {
                Toast.makeText(applicationContext, "Failed to fetch messages", Toast.LENGTH_SHORT).show()
            }
        })//end of helper

        //shared preference
        val prefs=applicationContext.getSharedPreferences("storage", MODE_PRIVATE)
        val chatNickname=prefs.getString("username","")
        //appending the userpreference to our textview
        val tV:TextView=findViewById(R.id.welcomeChatTv)
        tV.append(chatNickname)

        val chatMessage:EditText=findViewById(R.id.chatMessage)
        val chatBtn:Button=findViewById(R.id.chatBtn)
        chatBtn.setOnClickListener {
            val body=JSONObject()
            body.put("nickname",chatNickname)// picking the nickname from the preferences when we registered
            body.put("message",chatMessage.text.toString())
            helper.post(api,body)
            //refreshing chat activity
            startActivity(Intent(applicationContext,Chat::class.java))
        }

    }
}