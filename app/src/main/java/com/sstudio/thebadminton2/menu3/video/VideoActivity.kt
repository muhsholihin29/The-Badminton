package com.sstudio.thebadminton2.menu3.video

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sstudio.thebadminton2.R
import com.sstudio.thebadminton2.menu3.video.adapter.VideoAdapter
import com.sstudio.thebadminton2.core.data.Resource
import kotlinx.android.synthetic.main.activity_video.*
import org.koin.android.viewmodel.ext.android.viewModel

class VideoActivity : AppCompatActivity() {

    private lateinit var videoAdapter: VideoAdapter
    private val viewModel: VideoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        toolbar_video.title = "Rekam Video"
        setSupportActionBar(toolbar_video)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        swipe_refresh.setOnRefreshListener(videoRefresh)
        videoAdapter = VideoAdapter(this)

        rv_video.layoutManager = LinearLayoutManager(this)
        rv_video.adapter = videoAdapter
        videoAdapter.notifyDataSetChanged()

//        uploadPresenterImpl.getData()

        fab_video.setOnClickListener {
            startActivity(Intent(this, UploadActivity::class.java))
        }

        viewModel.getVideos?.observe(this){

            when (it){
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    it.data?.let {
                        videoAdapter.videos = it
                    }
                    videoAdapter.notifyDataSetChanged()

                }
            }
        }
    }

    private var videoRefresh: SwipeRefreshLayout.OnRefreshListener = SwipeRefreshLayout.OnRefreshListener{
        swipe_refresh.isRefreshing = false
    }

    fun videoOnLongClick(id: Int, videoUrl: String){
    }
}
