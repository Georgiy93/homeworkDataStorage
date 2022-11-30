package ru.netology.homeworkDataStorage.dto

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val like: Long = 0,
    val share: Long = 0,
    val view: Long = 0,
    val videoUrl:String,
    val likedByMe: Boolean = false,
    val sharedByMe: Boolean = false,
    val viewedByMe: Boolean = false
)