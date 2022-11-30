package ru.netology.homeworkDataStorage.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.homeworkDataStorage.activity.PostRepositoryFileImpl

import ru.netology.homeworkDataStorage.dto.Post
import ru.netology.homeworkDataStorage.repository.PostRepository
import ru.netology.homeworkDataStorage.repository.PostRepositoryInMemoryImpl

private val empty = Post(
    id = 0,
    author = "",
    content = "",
    published = "",
    videoUrl="",
    likedByMe = false,
    sharedByMe = false,
    viewedByMe = false,
    like = 0
)

class PostViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: PostRepository = PostRepositoryFileImpl(application)
    val data = repository.get()
    val edited = MutableLiveData(empty)

    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }

    fun edit(post: Post) {
        edited.value = post
    }

    fun changeContent(content: String) {

        edited.value?.let {

            val text = content.trim()
            if (it.content == text) {
                return
            }
            edited.value = it.copy(content = text)
        }

    }
    fun addVideoUrl(videoUrl:String) {
        edited.value?.let {

            val text = videoUrl.trim()
            if (it.videoUrl == text) {
                return
            }
            edited.value = it.copy(videoUrl = text)
        }
    }

    fun cancel() {

        edited.value = empty
    }

    fun likeById(id: Long) = repository.likeById(id)
    fun shareById(id: Long) = repository.shareById(id)
    fun viewById(id: Long) = repository.viewById(id)
    fun removeById(id: Long) = repository.removeById(id)
}
