package ru.netology.homeworkDataStorage.activity



import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import ru.netology.homeworkDataStorage.repository.PostRepository
import ru.netology.homeworkDataStorage.dto.Post

class PostRepositoryFileImpl(
    private val context: Context,

) : PostRepository {
    private val gson = Gson()
    private val type = TypeToken.getParameterized(List::class.java, Post::class.java).type
    private val filename = "posts30.json"
    private var nextId= 1L
    private var posts = listOf(
        Post(

            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "${nextId-1}пост: Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
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
            content = "${nextId-1}пост:: Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
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
            content = "${nextId-1}пост:: Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
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
            content = "${nextId-1}пост: Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false,
            videoUrl = "",
            sharedByMe = false,
            viewedByMe = false
        )

    )

    private val data = MutableLiveData(posts)

    init {
        val file = context.filesDir.resolve(filename)
        if (file.exists()) {
            // если файл есть - читаем
            context.openFileInput(filename).bufferedReader().use {
                posts = gson.fromJson(it, type)
                data.value = posts
            }
        } else {
            // если нет, записываем пустой массив

            sync()
        }
    }

    override fun get(): LiveData<List<Post>> = data

    override fun save(post: Post) {

        if (post.id == 0L) {
            nextId=posts[posts.lastIndex].id
            // TODO: remove hardcoded author & published
            posts =posts+listOf(
                post.copy(
                    id =nextId+1,
                    author = "Me",
                    likedByMe = false,
                    sharedByMe = false,
                    viewedByMe = false,
                    published = "now"
                )
            )
            data.value = posts
            sync()
            return
        }

        posts = posts.map {
            if (it.id != post.id) it else it.copy(content = post.content)
        }
        data.value = posts
        sync()
    }

    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(
                likedByMe = !it.likedByMe,
                like = if (it.likedByMe) it.like - 1 else it.like + 1
            )

        }
        data.value = posts
       sync()
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
      sync()
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
       sync()
    }

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
      sync()
    }

    private fun sync() {

        context.openFileOutput(filename, Context.MODE_PRIVATE).bufferedWriter().use {
            it.write(gson.toJson(posts))
        }
    }
}