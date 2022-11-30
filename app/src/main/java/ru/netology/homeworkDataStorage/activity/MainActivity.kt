package ru.netology.homeworkDataStorage.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.launch
import androidx.activity.viewModels
import ru.netology.homeworkDataStorage.R
import ru.netology.homeworkDataStorage.dto.adapter.OnInteractionListener
import ru.netology.homeworkDataStorage.dto.adapter.PostAdapter

import ru.netology.homeworkDataStorage.databinding.ActivityMainBinding
import ru.netology.homeworkDataStorage.dto.Post

import ru.netology.homeworkDataStorage.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {
    val viewModel: PostViewModel by viewModels()
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    val newPostLauncher = registerForActivityResult(NewPostResultContract()) { result ->
        result ?: return@registerForActivityResult

        viewModel.changeContent(result)
        viewModel.save()
    }

    val editPostLauncher = registerForActivityResult(EditPostResultContract()) { result ->
        result ?: return@registerForActivityResult
        viewModel.changeContent(result)
        viewModel.save()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()

        setupListeners()

    }

    private fun initViews() {

        val adapter = PostAdapter(object : OnInteractionListener {
            override fun onEdit(post: Post) {

                editPostLauncher.launch(post.content)

                viewModel.edit(post)
            }


            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun onView(post: Post) {
                viewModel.viewById(post.id)
            }

            override fun onShare(post: Post) {
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, post.content)
                    type = "text/plain"
                }

                val shareIntent =
                    Intent.createChooser(intent, getString(R.string.chooser_share_post))
                startActivity(shareIntent)
            }

            override fun onVideo(post: Post) {
                val intentVideo = Intent(Intent.ACTION_VIEW, Uri.parse(post.videoUrl))
                startActivity(intentVideo)

            }
        })
        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

    }


    private fun setupListeners() {

        binding.fab.setOnClickListener {
            newPostLauncher.launch()
        }

    }

}






