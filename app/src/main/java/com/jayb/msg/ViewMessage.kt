package com.jayb.msg

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONArray

class ViewMessage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_message)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, 0, systemBars.right, 0)
            insets
        }//of insets
        val progessBar:ProgressBar=findViewById(R.id.progressBar)
        val api="https://farasi.pythonanywhere.com/messages"
        val helper=ApiHelper(applicationContext)
        helper.get(api,object :ApiHelper.CallBack{
            override fun onSuccess(result: String?) {
                val messageJSONArray=JSONArray(result.toString())
                (0 until messageJSONArray.length()).forEach(){
                    val message=messageJSONArray.getJSONObject(it)
                    val messagetv:TextView=findViewById(R.id.tvMessage)
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
    }//end of onCreate
}//end of class