package com.jayb.msg

import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior

class Rchat : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_rchat)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Set soft input mode to adjust resize
//        val edit:EditText=findViewById(R.id.editTextMessage)
//        val inputMethodManager:InputMethodManager=getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        edit.setOnClickListener {
//            inputMethodManager.showSoftInput(edit,InputMethodManager.SHOW_IMPLICIT)
//        }

//        val bottom_sheet : FrameLayout = findViewById(R.id.frame)
//        BottomSheetBehavior.from(bottom_sheet).apply {
//            peekHeight = 250
//            this.state = BottomSheetBehavior.STATE_COLLAPSED
//        }

//
//        val recyclerView: RecyclerView =findViewById(R.id.recyclerViewChat)
//        val layout= LinearLayoutManager(applicationContext)
//        recyclerView.layoutManager=layout
//        val myAdapter=RecyclerAdapter(applicationContext)
//        recyclerView.adapter=myAdapter
        // Call this function to trigger API data fetching and populate the RecyclerView
//        myAdapter.refreshData()

    }
}