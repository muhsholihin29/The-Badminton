package com.sstudio.thebadminton.menu3.mvp.upload

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.DisplayMetrics
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
//import com.ipaulpro.afilechooser.utils.FileUtils
import com.sstudio.thebadminton.R
import com.sstudio.thebadminton.model.Video
import kotlinx.android.synthetic.main.activity_upload.*


class UploadActivity : AppCompatActivity(), UploadView {
    override fun showDialog() {
        dialog.show()
    }

    override fun dismissDialog() {
        dialog.dismiss()
    }

    override fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    override fun showVideoList(videos: List<Video>) {

    }

    override fun onProgressUpdate(percenage: Int) {
        dialog.progress = percenage
    }

    override fun showThumbnail(bitmap: Bitmap) {

    }


    private val REQUEST_PERMISSION = 1000
    private val PICK_FILE_REQUEST = 1001
    private var selectedFileUri: Uri? = null
    private lateinit var dialog: ProgressDialog
    private lateinit var mediaController: MediaController
    private val dm = DisplayMetrics()
    private lateinit var uploadPresenter: UploadPresenterImpl
    private lateinit var bitmap: Bitmap


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
        uploadPresenter = UploadPresenterImpl(this, this)
        dialog = ProgressDialog(this)
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
        dialog.setMessage("Uploading...")
        dialog.isIndeterminate = false
        dialog.max = 100
        dialog.setCancelable(false)

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
//                Log.d("taggUpload", "size $fileSize , file $size")
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
                    if (fileSize > 10485760) {
                        val dialog = AlertDialog.Builder(this)
                        dialog.setMessage("Video melebihi 10MB.Harap kompres video")
                        dialog.setNegativeButton(
                            "Oke"
                        ) { dialog, _ -> dialog.dismiss() }
                        dialog.show()
                    } else {
                        uploadPresenter.insertData(
                            "insert",
                            txt_name.text.toString(),
                            txt_title.text.toString(),
                            txt_overview.text.toString(),
                            selectedFileUri
                        )
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
                    selectedFileUri = data.data
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
//        val intent = Intent.createChooser(FileUtils.createGetContentIntent(), "Select a File")

//        startActivityForResult(intent, PICK_FILE_REQUEST)
    }
}
