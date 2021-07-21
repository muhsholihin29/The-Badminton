package com.sstudio.thebadminton2.menu3.video

import android.app.Activity
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sstudio.thebadminton2.BuildConfig.BASE_URL
import com.sstudio.thebadminton2.Common
import com.sstudio.thebadminton2.R
import com.sstudio.thebadminton2.menu3.video.adapter.CommentAdapter
import com.sstudio.thebadminton2.core.data.Resource
import com.sstudio.thebadminton2.core.domain.model.Comment
import com.sstudio.thebadminton2.core.domain.model.Video
import kotlinx.android.synthetic.main.activity_detail_video.*
import kotlinx.android.synthetic.main.content_detail.*
import org.koin.android.viewmodel.ext.android.viewModel
import tcking.github.com.giraffeplayer2.Option
import tcking.github.com.giraffeplayer2.PlayerManager
import tv.danmaku.ijk.media.player.IjkMediaPlayer


class DetailVideoActivity : AppCompatActivity() {
    companion object {
        val EXTRA_DETAIL = "extraa"
    }

    private val viewModel: VideoViewModel by viewModel()
    private val commentAdapter = CommentAdapter(this)
    private var video: Video? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_video)

        video = intent.getParcelableExtra(EXTRA_DETAIL)
        toolbar_video.title = video?.title
        setSupportActionBar(toolbar_video)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        rv_comment.layoutManager = LinearLayoutManager(this)
        rv_comment.adapter = commentAdapter


        PlayerManager.getInstance().defaultVideoInfo.addOption(
            Option.create(
                IjkMediaPlayer.OPT_CATEGORY_FORMAT,
                "multiple_requests",
                1L
            )
        )

        txt_overview.text = video?.overview
        txt_title.text = video?.title
        txt_name.text = video?.name
        video_view.setVideoPath(video?.videoUrl).player.start()

        if (!Common.isCoach && Common.androidId != video?.androidId) {
            comment_form.visibility = View.GONE
        }
        video?.id?.let { viewModel.videoId = it }
        viewModel.getComments?.observe(this) { resource ->
            observerComment(resource)
        }
        btn_comment.setOnClickListener {
            video?.let { submitComment(it) }
        }

        commentAdapter.onItemLongClick = {
            dialogDeleteComment(it.id)
        }
    }

    private fun observerComment(resource: Resource<List<Comment>>?) {
        when (resource) {
            is Resource.Loading -> {
                progress_bar.visibility = View.VISIBLE
            }
            is Resource.Success -> {
                resource.data?.let { commentAdapter.comments = it }
                commentAdapter.notifyDataSetChanged()
                progress_bar.visibility = View.GONE
            }
            is Resource.Error -> {
                progress_bar.visibility = View.GONE
                Toast.makeText(this, "${resource.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        @NonNull permissions: Array<String>,
        @NonNull grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
            } else {
                Toast.makeText(this, "please grant read permission", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        PlayerManager.getInstance().onConfigurationChanged(newConfig)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_video, menu)
        menu.findItem(R.id.menu_delete).isVisible =
            (Common.isCoach || Common.androidId == video?.androidId)
        menu.findItem(R.id.menu_save).isVisible = false
        menu.findItem(R.id.menu_edit).isVisible = false
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete -> {
                video?.let { dialogDeleteVideo(it) }
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun submitComment(video: Video) {
        val comment = et_comment.text.toString()
        et_comment.setText("")
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
        progress_bar.visibility = View.VISIBLE
        viewModel.insertComment(Comment(video.id, video.name, comment)).observe(this){ resource ->
            when (resource) {
                is Resource.Loading -> {
                    progress_bar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    viewModel.refreshComment().observe(this){
                        observerComment(it)
                    }
                }
                is Resource.Error -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(this, "${resource.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun dialogDeleteVideo(video: Video) {
        val dialogItems = arrayOf<CharSequence>("Hapus")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("")
        builder.setItems(dialogItems) { _, i ->
            when (i){
                0 -> viewModel.deleteVideo(video.id).observe(this){

                }
            }
        }
        builder.show()
    }

    private fun dialogDeleteComment(id: Int) {
        val dialogItems = arrayOf<CharSequence>("Hapus")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("")
        builder.setItems(dialogItems) { _, i ->
            when (i){
                0 -> viewModel.deleteComment(id).observe(this){

                }
            }
        }
        builder.show()
    }

}
