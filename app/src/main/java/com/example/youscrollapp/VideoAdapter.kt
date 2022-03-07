package com.example.youscrollapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.youscrollapp.databinding.ActivityMainBinding
import com.example.youscrollapp.databinding.ItemVideoBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class VideoAdapter(private val list: List<VideoItem>):
    RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding:ItemVideoBinding = ItemVideoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setVideoData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val videoView: YouTubePlayerView = itemView.findViewById(R.id.videoView)
        val textView:TextView = itemView.findViewById(R.id.viewTitle)


    fun setVideoData(videoItem: VideoItem){
        textView.text = videoItem.videoTitle
        videoView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer) {
                val videoId = videoItem.videoID
                youTubePlayer.cueVideo(videoId, 0F)
            }})


            }
        }



}
