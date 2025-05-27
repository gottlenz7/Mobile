package ru.fefu.homeworkproject

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import ru.fefu.homeworkproject.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupPrivacyText()


        binding.registerButton.setOnClickListener {
        }

        binding.backArrow.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    fun setupPrivacyText() {
        val privacyText = "Нажимая на кнопку, вы соглашаетесь с политикой конфиденциальности и обработки персональных данных, а также принимаете пользовательское соглашение."

        val spannable = SpannableString(privacyText)

        val privacyStart = privacyText.indexOf("политикой конфиденциальности")
        if (privacyStart != -1) {
            val privacyEnd = privacyStart + "политикой конфиденциальности".length
            spannable.setSpan(
                ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorPrimary)),
                privacyStart,
                privacyEnd,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        val agreementStart = privacyText.indexOf("пользовательское соглашение")
        if (agreementStart != -1) {
            val agreementEnd = agreementStart + "пользовательское соглашение".length
            spannable.setSpan(
                ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorPrimary)),
                agreementStart,
                agreementEnd,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        binding.privacyTextView.text = spannable
    }
}