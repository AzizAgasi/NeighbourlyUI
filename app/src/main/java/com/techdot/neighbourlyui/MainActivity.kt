package com.techdot.neighbourlyui

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.navigation.NavController
import com.techdot.neighbourlyui.databinding.ActivityMainBinding
import com.techdot.neighbourlyui.model.Post
import com.techdot.neighbourlyui.ui.PostAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val post1: Post = Post(R.drawable.profile,
            "Aziz Agasi",
            "Dubai",
        "What are some of the top places to visit in India during a visit? PS I have visited places like Pune, Mumbai and Chennai",
        R.drawable.profile,
        "Anonymous",
        "Well I guess you have visited most of the places but I'd suggest you to go to Rajasthan. The sites there are amazing and breathe taking",
        2,
        null,
        null)

        val post2: Post = Post(R.drawable.profile,
            "Aziz Agasi",
            "Dubai",
            "What are some of the top places to visit in India during a visit? PS I have visited places like Pune, Mumbai and Chennai",
            R.drawable.profile,
            "Anonymous",
            "Well I guess you have visited most of the places but I'd suggest you to go to Rajasthan. The sites there are amazing and breathe taking",
            2,
            R.drawable.image_post,
        "Buildings somewhere")

        val posts = java.util.ArrayList<Post>()

        posts.add(post1)
        posts.add(post2)

        val adapter = PostAdapter(this, posts, supportFragmentManager)

        binding.recyclerView.adapter = adapter

        binding.location.setOnClickListener {
            getCustomDialog()
        }
    }

    private fun getCustomDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        dialog.apply {
            window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setContentView(R.layout.neighbourhood_change_layout)
            setCanceledOnTouchOutside(true)
            setCancelable(true)
            show()

            val window = this.window
            val windowManager = window!!.attributes

            windowManager.gravity = Gravity.TOP
            windowManager.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
            window.attributes = windowManager
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        }
    }


}