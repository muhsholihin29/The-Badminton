package com.sstudio.thebadminton2.menu3.video

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sstudio.thebadminton2.core.data.Resource
import com.sstudio.thebadminton2.core.domain.model.Comment
import com.sstudio.thebadminton2.core.domain.model.Video
import com.sstudio.thebadminton2.core.domain.usecase.TheBadmintonUseCase

class VideoViewModel(private val theBadmintonUseCase: TheBadmintonUseCase): ViewModel() {
    var getVideos: LiveData<Resource<List<Video>>>? = null
        get() {
            if (field == null) {
                field = MutableLiveData()
                field = theBadmintonUseCase.getVideos().asLiveData()
            }
            return field
        }
        private set

    fun insertVideo(video: Video, videoUri: Uri): LiveData<Resource<String>> {
        return theBadmintonUseCase.insertVideo(video = video, videoUri = videoUri).asLiveData()
    }

    var videoId = 0
    var getComments: LiveData<Resource<List<Comment>>>? = null
        get() {
            if (field == null) {
                field = MutableLiveData()
                field = theBadmintonUseCase.getComments(videoId).asLiveData()
            }
            return field
        }
        private set

    fun refreshComment(): LiveData<Resource<List<Comment>>> {
        return theBadmintonUseCase.getComments(videoId = videoId).asLiveData()
    }

    fun insertComment(comment: Comment): LiveData<Resource<Void>> {
        return theBadmintonUseCase.insertComment(videoId = videoId, comment = comment).asLiveData()
    }

    fun deleteComment(commentId: Int): LiveData<Resource<Void>> {
        return theBadmintonUseCase.deleteComment(commentId).asLiveData()
    }

    fun deleteVideo(videoId: Int): LiveData<Resource<Void>> {
        return theBadmintonUseCase.deleteVideo(videoId).asLiveData()
    }
}