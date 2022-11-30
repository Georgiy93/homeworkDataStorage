package ru.netology.homeworkDataStorage.repository;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import ru.netology.homeworkDataStorage.dto.Post

class PostRepositoryInMemoryImpl : PostRepository {
    private var nextId = 1L

    private var posts = listOf(
        Post(

            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = " ${nextId - 1} пост: Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false,
            sharedByMe = false,
            viewedByMe = false,
            videoUrl = "https://www.youtube.com/watch?v=QUvVdTlA23w",
            like = 6,
            share = 5
        ),
        Post(

            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "${nextId - 1} пост:: Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false,
            sharedByMe = false,
            viewedByMe = false,
            videoUrl = "",
            like = 2,
            share = 2,
            view = 2,
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "${nextId - 1} пост:: Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false,
            sharedByMe = false,
            viewedByMe = false,
            videoUrl = "",
            like = 4,
            share = 1,
            view = 1,
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "${nextId - 1} пост: Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false,
            videoUrl = "",
            sharedByMe = false,
            viewedByMe = false
        )

    )
    private val data = MutableLiveData(posts)

    override fun get(): LiveData<List<Post>> = data

    override fun save(post: Post) {

        if (post.id == 0L) {
            posts = listOf(
                post.copy(
                    id = nextId++, author = "Me", published = "now"
                )
            )+posts
                    data.value = posts
            return
        }
        posts = posts.map {
            if (it.id != post.id) it else it.copy(content = post.content)
        }
        data.value = posts
    }


    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id == id) {
                it.copy(
                    likedByMe = !it.likedByMe,
                    like = if (it.likedByMe) it.like - 1 else it.like + 1
                )
            } else {
                it
            }
        }

        data.value = posts

    }

    override fun shareById(id: Long) {
        posts = posts.map {
            if (it.id == id) {
                it.copy(
                    sharedByMe = !it.sharedByMe,
                    share = it.share + 10_000

                )
            } else {
                it
            }
        }
        data.value = posts

    }

    override fun viewById(id: Long) {
        posts = posts.map {
            if (it.id == id) {
                it.copy(
                    viewedByMe = !it.viewedByMe,
                    view = it.view + 10_000
                )
            } else {
                it
            }
        }

        data.value = posts
    }

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }
}