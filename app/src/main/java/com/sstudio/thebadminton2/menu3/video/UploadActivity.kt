package com.sstudio.thebadminton2.menu3.video

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.jaiselrahman.filepicker.activity.FilePickerActivity
import com.jaiselrahman.filepicker.config.Configurations
import com.jaiselrahman.filepicker.model.MediaFile
import com.sstudio.thebadminton2.Common
import com.sstudio.thebadminton2.R
import com.sstudio.thebadminton2.core.data.Resource
import com.sstudio.thebadminton2.core.domain.model.Video
import kotlinx.android.synthetic.main.activity_upload.*
import org.koin.android.viewmodel.ext.android.viewModel


class UploadActivity : AppCompatActivity() {

    private val REQUEST_PERMISSION = 1000
    private val PICK_FILE_REQUEST = 1001
    private var selectedFileUri: Uri? = null
    private val viewModel: VideoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)

        toolbar_upload.title = "Tambah Video"
        setSupportActionBar(toolbar_upload)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_PERMISSION
            )
        }

        vv_video_upload.visibility = View.GONE

        fab_upload.setOnClickListener { chooseFile() }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_video, menu)
        menu.findItem(R.id.menu_edit).isVisible = false
        menu.findItem(R.id.menu_delete).isVisible = false
        menu.findItem(R.id.menu_save).isVisible = true

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {

                this.finish()

                return true
            }
            R.id.menu_save -> {
                //Save
                val afd = contentResolver.openAssetFileDescriptor(selectedFileUri!!, "r")
                val fileSize = afd?.length
                afd?.close()
                if (TextUtils.isEmpty(txt_name.text.toString()) ||
                    TextUtils.isEmpty(txt_title.text.toString()) ||
                    TextUtils.isEmpty(txt_overview.text.toString())
                ) {
                    val alertDialog = AlertDialog.Builder(this)
                    alertDialog.setMessage("Please complete the field!")
                    alertDialog.setPositiveButton(
                        "Ok"
                    ) { dialog, _ -> dialog.dismiss() }
                    alertDialog.show()
                } else if (fileSize != null) {
                    if (fileSize >= 16777216) {
                        val dialog = AlertDialog.Builder(this)
                        dialog.setMessage("Video melebihi 16MB.Harap kompres video")
                        dialog.setNegativeButton(
                            "Oke"
                        ) { dialog, _ -> dialog.dismiss() }
                        dialog.show()
                    } else {
                        viewModel.insertVideo(Video(
                            title = txt_title.text.toString(),
                            name = txt_name.text.toString(),
                            overview = txt_overview.text.toString(),
                            androidId = Common.androidId
                        ), selectedFileUri!!).observe(this){
                            when(it){
                                is Resource.Loading -> {
                                    progress_bar.visibility = View.VISIBLE
                                }
                                is Resource.Success -> {
                                    progress_bar.visibility = View.GONE
                                    startActivity(Intent(this, VideoActivity::class.java))
                                    finish()
                                }
                                is Resource.Error -> {
                                    progress_bar.visibility = View.GONE
                                    Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                }
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PICK_FILE_REQUEST) {
                if (data != null) {
                    val files: ArrayList<MediaFile>? =
                        data.getParcelableArrayListExtra(FilePickerActivity.MEDIA_FILES)
                    selectedFileUri = files?.first()?.uri
                    Log.d("mytag", "$selectedFileUri")
                    if (selectedFileUri != null && selectedFileUri!!.path!!.isNotEmpty()) {
                        vv_video_upload.visibility = View.VISIBLE
                        vv_video_upload.setVideoPath(selectedFileUri.toString()).player.stop()
                    } else {
                        Toast.makeText(this, "cannot upload file to server", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    private fun chooseFile() {
        val intent = Intent(this, FilePickerActivity::class.java)
        intent.putExtra(FilePickerActivity.CONFIGS, Configurations.Builder()
            .setSingleChoiceMode(true)
            .setSuffixes("mp4","avi","3gp","mkv")
            .setShowVideos(true)
            .build())

        startActivityForResult(intent, PICK_FILE_REQUEST)
    }
}
