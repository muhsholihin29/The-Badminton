package com.sstudio.thebadminton.menu3

import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.sstudio.thebadminton.BuildConfig
import com.sstudio.thebadminton.R
import com.sstudio.thebadminton.model.Video
import kotlinx.android.synthetic.main.activity_detail_video.*
import kotlinx.android.synthetic.main.content_detail.*
import tcking.github.com.giraffeplayer2.PlayerManager
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import com.sstudio.thebadminton.adapter.CommentAdapter
import com.sstudio.thebadminton.menu3.mvp.comment.CommentPresenterImpl
import com.sstudio.thebadminton.menu3.mvp.comment.DetailView
import com.sstudio.thebadminton.model.Comment
import tcking.github.com.giraffeplayer2.Option
import tv.danmaku.ijk.media.player.IjkMediaPlayer
import java.security.AccessController.getContext
import android.app.Activity
import android.app.AlertDialog
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.media.MediaPlayer
import android.net.Uri
import android.text.TextUtils
import android.util.DisplayMetrics
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import com.sstudio.thebadminton.BuildConfig.BASE_URL
import com.sstudio.thebadminton.Common
import kotlinx.android.synthetic.main.activity_upload.*
import kotlinx.android.synthetic.main.content_detail.txt_name
import kotlinx.android.synthetic.main.content_detail.txt_overview
import kotlinx.android.synthetic.main.content_detail.txt_title
import tcking.github.com.giraffeplayer2.MediaController
import tcking.github.com.giraffeplayer2.VideoView


class DetailVideoActivity : AppCompatActivity(), DetailView {
    override fun refreshComment() {
        video?.id?.let { commentPresenterImpl.getComment(it) }
    }

    override fun showComment(comment: List<Comment>) {
        commentAdapter.comments = comment
        commentAdapter.notifyDataSetChanged()
        pb_comment.visibility = View.GONE
    }

    override fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    companion object {
        val EXTRA_DETAIL = "extraa"
    }

    private val commentAdapter = CommentAdapter(this)
    private lateinit var commentPresenterImpl: CommentPresenterImpl
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

        commentPresenterImpl = CommentPresenterImpl(this, this)

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
        video_view.setVideoPath(BASE_URL + video?.videoUrl).player.start()

        if (video?.androidId != Common.androidId && !Common.isCoach){
            comment_form.visibility = View.GONE
        }
        video?.let {
            it.id?.let { it1 -> commentPresenterImpl.getComment(it1) }
            btn_comment.setOnClickListener{ _ ->
                submitComment(it)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>, @NonNull grantResults: IntArray) {
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
        menu.findItem(R.id.menu_delete).isVisible = (Common.isCoach || Common.androidId == video?.androidId)
        menu.findItem(R.id.menu_save).isVisible = false
        menu.findItem(R.id.menu_edit).isVisible = false
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete -> {
                video?.id?.let { video?.videoUrl?.let { it1 ->
                    commentPresenterImpl.deleteVideo(it,
                        it1
                    )
                } }
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun submitComment(video: Video){
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
        pb_comment.visibility = View.VISIBLE
        video.id?.let { commentPresenterImpl.addComment(it, video.name, comment) }
    }

    fun commentOnLongClick(id: String){
        commentPresenterImpl.dialogCommentOption(id)
    }


}
