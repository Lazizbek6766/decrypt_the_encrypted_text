package com.example.myapplicationbmi

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplicationbmi.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {
    lateinit var binding: ActivityMain3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            button.setOnClickListener {
                val matn1 = matn.text.toString()
                val k = kalit.text.toString()

                if (matn1.isEmpty() && k.isEmpty()){
                    matn.error = "Matn qatori bo'sh"
                    kalit.error = "K kiritilmagan"
                }else if (matn1.isEmpty()){
                    matn.error = "Matn qatori bo'sh"
                }else if (k.isEmpty()){
                    kalit.error = "K kiritilmagan"
                }else{
                    shifrMatn2.text = encrypt(matn1, k.toInt())
                }
            }
            copy.setOnClickListener {
                val text = shifrMatn2.text.toString()
                copyTextToClipboard(text)
            }
        }

    }

    fun encrypt(text: String, k: Int): String {
        val encryptedText = StringBuilder()
        for (char in text) {
            val encryptedChar = encryptChar(char, k)
            encryptedText.append(encryptedChar)
        }
        return encryptedText.toString()
    }

    fun encryptChar(char: Char, k: Int): Char {
        if (char in 'a'..'z') {
            val shiftedChar = ((char.toInt() - 'a'.toInt() + k) % 26 + 'a'.toInt()).toChar()
            return shiftedChar
        }
        if (char in 'A'..'Z') {
            val shiftedChar = ((char.toInt() - 'A'.toInt() + k) % 26 + 'A'.toInt()).toChar()
            return shiftedChar
        }
        return char
    }
    private fun copyTextToClipboard(text: String) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("label", text)
        clipboard.setPrimaryClip(clip)
    }

}