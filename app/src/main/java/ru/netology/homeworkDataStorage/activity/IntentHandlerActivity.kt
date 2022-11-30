package ru.netology.homeworkDataStorage.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_INDEFINITE
import ru.netology.homeworkDataStorage.databinding.ActivityIntentHandlerBinding
import com.google.android.material.snackbar.Snackbar
import ru.netology.homeworkDataStorage.R

class IntentHandlerActivity : AppCompatActivity() {
    lateinit var binding: ActivityIntentHandlerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityIntentHandlerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadIntent()

    }

    private fun loadIntent() {
        intent?.let {
            if (it.action != Intent.ACTION_SEND) {
                return@let
            }

            val text = it.getStringExtra(Intent.EXTRA_TEXT)
            if (text.isNullOrBlank()) {
                Snackbar.make(binding.root, R.string.error_empty_content, LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok) {
                        finish()
                    }
                    .show()
                return@let
            }
            else{
                binding.postText.text=text
            }
            // TODO: handle text
        }
    }
}