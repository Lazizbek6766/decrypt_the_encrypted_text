package com.example.myapplicationbmi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationbmi.databinding.ActivityMainBinding
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var array: ArrayList<Modul> = arrayListOf()
    lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {


            next.setOnClickListener {
                val intent = Intent(this@MainActivity, MainActivity3::class.java)
                startActivity(intent)
            }
            button.setOnClickListener {

                val text = shifrMatn.text.toString()

                if (text.isEmpty()) {
                    shifrMatn.error = "Matn qatori bo'sh"
                } else {

                    array.clear()

                    val frequencies = HashMap<Char, Int>()

                    // Harflarni sanash va chastotalarni hisoblash
                    for (char in text) {
                        if (char.isLetter()) {
                            val lowercaseChar = char.toLowerCase()
                            frequencies[lowercaseChar] =
                                frequencies.getOrDefault(lowercaseChar, 0) + 1
                        }
                    }

                    // Harflarni chastotaga ko'ra tartiblash
                    val sortedFrequencies = frequencies.entries.sortedByDescending { it.value }
                    val chastata = arrayListOf<Char>('e', 't', 'a', 'o')
                    val totalLetters = text.count { it.isLetter() }.toFloat()

                    var k = 0

                    for (i in 0..3) {

                        k = sortedFrequencies[0].key.toInt() - chastata[i].toInt()

                        // "K" berilgan sezr shifrlash algoritmidan deshifrlash uchun
                        val decryptedText = decrypt(text, k)

                        array.add(
                            Modul(
                                sortedFrequencies[0].key.toString(),chastata[i].toString(),"K = $k", decryptedText
                            )
                        )

                    }

                    Toast.makeText(this@MainActivity,"${array[0].k}", Toast.LENGTH_SHORT).show()
                    recycler.layoutManager = LinearLayoutManager(this@MainActivity)
                    recyclerAdapter = RecyclerAdapter(this@MainActivity, array)
                    recycler.adapter = recyclerAdapter

                }
            }
        }

    }

    fun decrypt(text: String, k: Int): String {
        val decryptedText = StringBuilder()
        for (char in text) {
            val decryptedChar = decryptChar(char, k)
            decryptedText.append(decryptedChar)
        }
        return decryptedText.toString()
    }

    fun decryptChar(char: Char, k: Int): Char {
        if (char in 'a'..'z') {
            val shiftedChar = ((char.toInt() - 'a'.toInt() - k + 26) % 26 + 'a'.toInt()).toChar()
            return shiftedChar
        }
        if (char in 'A'..'Z') {
            val shiftedChar = ((char.toInt() - 'A'.toInt() - k + 26) % 26 + 'A'.toInt()).toChar()
            return shiftedChar
        }
        return char
    }


}