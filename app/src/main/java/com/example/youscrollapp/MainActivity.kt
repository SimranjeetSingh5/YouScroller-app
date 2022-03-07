package com.example.youscrollapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.youscrollapp.databinding.ActivityMainBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private var counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Toast.makeText(this,"Scroll Up",Toast.LENGTH_LONG).show()

        val videoIDList = ArrayList<VideoItem>()

        videoIDList.add(VideoItem("IEF6mw7eK4s","1"))
        videoIDList.add(VideoItem("8CEJoCr_9UI","2"))
        videoIDList.add(VideoItem("344u6uK9qeQ","3"))
        videoIDList.add(VideoItem("3-nM3yNi3wg","4"))
        videoIDList.add(VideoItem("RlcY37n5j9M","5"))
        videoIDList.add(VideoItem("nB7nGcW9TyE","6"))

        binding.viewPager.adapter = VideoAdapter(videoIDList)
        binding.nextBtn.setOnClickListener {
            val intent  = Intent(this,FormActivity::class.java)
            startActivity(intent)
        }
       }
}