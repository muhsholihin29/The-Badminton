package com.sstudio.thebadminton2.core.data

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.Base64
import com.sstudio.thebadminton2.core.data.remote.RemoteDataSource
import com.sstudio.thebadminton2.core.data.remote.network.ApiResponse
import com.sstudio.thebadminton2.core.domain.model.Comment
import com.sstudio.thebadminton2.core.domain.model.Note
import com.sstudio.thebadminton2.core.domain.model.Video
import com.sstudio.thebadminton2.core.domain.repository.ITheBadmintonRepository
import com.sstudio.thebadminton2.core.utils.DataMapper
import com.sstudio.thebadminton2.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream


class TheBadmintonRepository(
    private val remoteDataSource: RemoteDataSource,
    private val context: Context
) :
    ITheBadmintonRepository {
    override fun getVideos(): Flow<Resource<List<Video>>> = flow {
        emit(Resource.Loading())
        emitAll(remoteDataSource.getVideos().map { response ->

            when (response) {
                is ApiResponse.Success -> {
                    Resource.Success(response.data.data.map { DataMapper.mapVideoResponseToDomain(it) })
                }
                is ApiResponse.Empty -> {
                    Resource.Success(emptyList())
                }
                is ApiResponse.Error -> {
                    Resource.Error(response.errorMessage)
                }
            }
        })
    }

    override fun insertVideo(video: Video, videoUri: Uri): Flow<Resource<String>> = flow {

        emit(Resource.Loading())
        emitAll(
            coroutineScope {
                val newUri = Uri.fromFile(Utils.getFile(context, videoUri))
//                val mmr = FFmpegMediaMetadataRetriever()
//                mmr.setDataSource(newUri.toString())

                val bitmap =
                    withContext(Dispatchers.Default) {
                        Utils.getThumbnailVideo(
                            newUri,
                            context
                        )
                    }
//                val bitmap = mmr.getFrameAtTime(2000000, FFmpegMediaMetadataRetriever.OPTION_CLOSEST)
                val fileThumbnail = Utils.bitmapToFile(bitmap!!, context)
//                Log.d("mytag", getStringImage(bitmap))

                remoteDataSource.insertVideo(video, Utils.getFile(context, videoUri), fileThumbnail).map { response ->

                    when (response) {
                        is ApiResponse.Success -> {
                            Resource.Success("ss")
                        }
                        is ApiResponse.Empty -> {
                            Resource.Success("")
                        }
                        is ApiResponse.Error -> {
                            Resource.Error(response.errorMessage)
                        }
                    }
                }
            })
    }.flowOn(Dispatchers.Default)

    override fun deleteVideo(videoId: Int): Flow<Resource<Void>> = flow {
        emit(Resource.Loading())
        emitAll(remoteDataSource.deleteVideo(videoId).map { response ->
            when (response) {
                is ApiResponse.Success -> {
                    Resource.Success<Void>(null)
                }
                is ApiResponse.Empty -> {
                    Resource.Success<Void>(null)
                }
                is ApiResponse.Error -> {
                    Resource.Error(response.errorMessage)
                }
            }
        })
    }

    override fun getComments(videoId: Int): Flow<Resource<List<Comment>>> = flow {
        emit(Resource.Loading())
        emitAll(remoteDataSource.getComments(videoId).map { response ->
            when (response) {
                is ApiResponse.Success -> {
                    Resource.Success(response.data.data.map { DataMapper.mapCommentResponseToDomain(it) })
                }
                is ApiResponse.Empty -> {
                    Resource.Success(emptyList())
                }
                is ApiResponse.Error -> {
                    Resource.Error(response.errorMessage)
                }
            }
        })
    }

    override fun insertComment(videoId: Int, comment: Comment): Flow<Resource<Void>> = flow {
        emit(Resource.Loading())
        emitAll(
            remoteDataSource.insertComment(videoId = videoId, comment = comment).map { response ->
                when (response) {
                    is ApiResponse.Success -> {
                        Resource.Success<Void>(null)
                    }
                    is ApiResponse.Empty -> {
                        Resource.Success<Void>(null)
                    }
                    is ApiResponse.Error -> {
                        Resource.Error(response.errorMessage)
                    }
                }
            })
    }

    override fun deleteComment(commentId: Int): Flow<Resource<Void>> = flow {
        emit(Resource.Loading())
        emitAll(remoteDataSource.deleteComment(commentId).map { response ->
            when (response) {
                is ApiResponse.Success -> {
                    Resource.Success<Void>(null)
                }
                is ApiResponse.Empty -> {
                    Resource.Success<Void>(null)
                }
                is ApiResponse.Error -> {
                    Resource.Error(response.errorMessage)
                }
            }
        })
    }

    override fun getNote(androidId: String): Flow<Resource<List<Note>>> = flow {
        emit(Resource.Loading())
        emitAll(remoteDataSource.getNote(androidId).map { response ->
            when (response) {
                is ApiResponse.Success -> {
                    Resource.Success(response.data.data.map { DataMapper.mapNoteResponseToDomain(it) })
                }
                is ApiResponse.Empty -> {
                    Resource.Success(emptyList())
                }
                is ApiResponse.Error -> {
                    Resource.Error(response.errorMessage)
                }
            }
        })
    }

    override fun insertNote(note: Note): Flow<Resource<Void>> = flow {
        emit(Resource.Loading())
        emitAll(remoteDataSource.insertNote(note).map { response ->
            when (response) {
                is ApiResponse.Success -> {
                    Resource.Success<Void>(null)
                }
                is ApiResponse.Empty -> {
                    Resource.Success<Void>(null)
                }
                is ApiResponse.Error -> {
                    Resource.Error(response.errorMessage)
                }
            }
        })
    }

    override fun deleteNote(noteId: Int): Flow<Resource<Void>> = flow {
        emit(Resource.Loading())
        emitAll(remoteDataSource.deleteNote(noteId).map { response ->
            when (response) {
                is ApiResponse.Success -> {
                    Resource.Success<Void>(null)
                }
                is ApiResponse.Empty -> {
                    Resource.Success<Void>(null)
                }
                is ApiResponse.Error -> {
                    Resource.Error(response.errorMessage)
                }
            }
        })
    }

    override fun updateNote(note: Note): Flow<Resource<Void>> = flow {
        emit(Resource.Loading())
        emitAll(remoteDataSource.updateNote(note).map { response ->
            when (response) {
                is ApiResponse.Success -> {
                    Resource.Success<Void>(null)
                }
                is ApiResponse.Empty -> {
                    Resource.Success<Void>(null)
                }
                is ApiResponse.Error -> {
                    Resource.Error(response.errorMessage)
                }
            }
        })
    }
}