package com.sstudio.thebadminton.menu3

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sstudio.thebadminton.R
import com.sstudio.thebadminton.adapter.VideoAdapter
import com.sstudio.thebadminton.menu3.mvp.upload.UploadActivity
import com.sstudio.thebadminton.menu3.mvp.upload.UploadPresenterImpl
import com.sstudio.thebadminton.menu3.mvp.upload.UploadView
import com.sstudio.thebadminton.model.Video
import kotlinx.android.synthetic.main.activity_video.*
import android.view.LayoutInflater
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.ContextThemeWrapper


class VideoActivity : AppCompatActivity(), UploadView {
    override fun showThumbnail(bitmap: Bitmap) {

    }

    override fun onProgressUpdate(percenage: Int) {

    }

    override fun showDialog() {
        pb_video_list.visibility = View.VISIBLE
    }

    override fun dismissDialog() {
        pb_video_list.visibility = View.GONE
    }

    override fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun showVideoList(videos: List<Video>) {
        videoAdapter.videos = videos
        videoAdapter.notifyDataSetChanged()
        dismissDialog()
    }

    private lateinit var videoAdapter: VideoAdapter
    private val uploadPresenterImpl =
        UploadPresenterImpl(this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        toolbar_video.title = "Rekam Video"
        setSupportActionBar(toolbar_video)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        showDialog()
        swipe_refresh.setOnRefreshListener(videoRefresh)
        videoAdapter = VideoAdapter(this)

        rv_video.layoutManager = LinearLayoutManager(this)
        rv_video.adapter = videoAdapter
        videoAdapter.notifyDataSetChanged()

        uploadPresenterImpl.getData()

        fab_video.setOnClickListener {
            startActivity(Intent(this, UploadActivity::class.java))
        }
    }

    private var videoRefresh: SwipeRefreshLayout.OnRefreshListener = SwipeRefreshLayout.OnRefreshListener{
        swipe_refresh.isRefreshing = false
        uploadPresenterImpl.getData()
    }

    fun videoOnLongClick(id: String, videoUrl: String){
        uploadPresenterImpl.deleteVideo(id,videoUrl)
    }
}
