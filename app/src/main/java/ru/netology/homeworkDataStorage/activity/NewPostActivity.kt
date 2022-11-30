package ru.netology.homeworkDataStorage.activity


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.homeworkDataStorage.databinding.ActivityNewPostBinding

class NewPostActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewPostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        returnIntent()
    }

    private fun returnIntent() {
        binding.edit.requestFocus()
        binding.save.setOnClickListener {
            val intent = Intent()
            if (binding.edit.text.isNullOrBlank()) {
                setResult(Activity.RESULT_CANCELED, intent)
            } else {
                val content = binding.edit.text.toString()
                intent.putExtra(Intent.EXTRA_TEXT, content)
                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }
    }
}