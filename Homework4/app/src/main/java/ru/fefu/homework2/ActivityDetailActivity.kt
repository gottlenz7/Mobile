package ru.fefu.homework2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ActivityDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val titleText = findViewById<TextView>(R.id.titleText)
        val distanceText = findViewById<TextView>(R.id.distanceText)
        val timeAgoText = findViewById<TextView>(R.id.timeAgoText)
        val durationText = findViewById<TextView>(R.id.durationText)
        val timeRangeText = findViewById<TextView>(R.id.timeRangeText)
        val commentText = findViewById<TextView>(R.id.commentText)

        titleText.text = "Серфинг"
        distanceText.text = "14.32 км"
        timeAgoText.text = "14 часов назад"
        durationText.text = "2 ч 46 мин"
        timeRangeText.text = "Старт 14:49 | Финиш 16:31"
        commentText.text = "Комментарий"

        val backButton: ImageButton = findViewById(R.id.backButton)

        backButton.setOnClickListener {
            finish()
        }
    }
}