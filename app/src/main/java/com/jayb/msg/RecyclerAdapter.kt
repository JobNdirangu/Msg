package com.jayb.msg

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject

class RecyclerAdapter(private var context: Context) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    val messagesList = mutableListOf<JSONObject>()
    //inflate the single item layout
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.chattemplate, viewGroup, false)
        return ViewHolder(v)
    }
    //finding the views in the single item layout
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val senderTextView: TextView = itemView.findViewById(R.id.chatSenderId)
        val chatTextView: TextView = itemView.findViewById(R.id.chatMessageId)
    }

    // Function to fetch data from the API
    private fun fetchDataFromApi() {
        val api="https://farasi.pythonanywhere.com/messages"
        val helper = ApiHelper(context)
        helper.get(api, object : ApiHelper.CallBack {
            override fun onSuccess(result: String?) {
                val messageJSONArray = JSONArray(result ?: "")
                // Process the JSONArray result and update adapter data
                messagesList.clear()
                for (i in 0 until messageJSONArray.length()) {
                    val message = messageJSONArray.getJSONObject(i)
                    messagesList.add(message)
                }
                this@RecyclerAdapter.notifyDataSetChanged() // Notify RecyclerView that the data has changed
            }
            // Implement onFailure method from ApiHelper.CallBack interface
            override fun onFailure(error: Throwable?) {
                Toast.makeText(context, "Error Occurred", Toast.LENGTH_LONG).show()
            }
        })

    }

    // Call this function from the activity/fragment to trigger API data fetching
    fun refreshData() {
        fetchDataFromApi()
    }
    //binding of the data to respective views in the recycler
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val currentItem = messagesList[i]
        viewHolder.senderTextView.text = "Sender: ${currentItem.getString("nickname")}"
        viewHolder.chatTextView.text = "Message: ${currentItem.getString("message")}"
        viewHolder.itemView.setOnClickListener {
            Toast.makeText(context, "Hello this is my position:", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return messagesList.size
    }
}
