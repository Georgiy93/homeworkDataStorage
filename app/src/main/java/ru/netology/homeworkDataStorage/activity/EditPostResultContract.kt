package ru.netology.homeworkDataStorage.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract



class EditPostResultContract() : ActivityResultContract<String, String?>() { // Допускаем, что входным значением будет тип `String`
    override fun createIntent(
        context: Context,
        input: String
    ) = Intent(context, EditPostActivity::class.java).apply {
        putExtra(Intent.EXTRA_TEXT, input)

    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        if (resultCode != Activity.RESULT_OK)  return null
        return intent?.getStringExtra(Intent.EXTRA_TEXT)
    }

}

