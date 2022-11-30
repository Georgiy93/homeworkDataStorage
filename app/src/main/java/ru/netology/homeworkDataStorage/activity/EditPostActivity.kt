package ru.netology.homeworkDataStorage.activity


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.homeworkDataStorage.databinding.ActivityEditBinding

class EditPostActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.content.text = intent.getStringExtra(Intent.EXTRA_TEXT)

        binding.content.requestFocus()


        binding.save.setOnClickListener {
            val intent = Intent()
            if (binding.content.text.isNullOrBlank()) {
                setResult(Activity.RESULT_CANCELED, intent)
            } else {
                val content = binding.content.text.toString()
                intent.putExtra(Intent.EXTRA_TEXT, content)
                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }

    }
}