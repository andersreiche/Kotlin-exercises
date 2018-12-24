package com.example.anders.exercise1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import kotlin.collections.sort


class MainActivity : AppCompatActivity() {

    var radioButtonSelected = "radioButton"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val radioGroup = findViewById<RadioGroup>(R.id.radio_group)
        radioGroup?.setOnCheckedChangeListener { group, checkedId ->

            if (R.id.radioButton == checkedId) {
                radioButtonSelected = "radioButton"
            }

            if (R.id.radioButton2 == checkedId) {
                radioButtonSelected = "radioButton2"
            }
        }
    }

    fun buttonRun(v: View) {
        if (radioButtonSelected == "radioButton") {
            val charArray = editText.text.toString().toCharArray()
            val sortedCharArray = charArray.sortedArray()
            val sortedString= String(sortedCharArray)
            textView2.text = "[" + sortedString + "]"
        }

        if (radioButtonSelected == "radioButton2") {
            val stringArray = editText.text.toString().split(" ")
            var longestString = ""

            for (string in stringArray) {
                if (longestString.length < string.length)
                    longestString = string
            }

            textView2.text = longestString
        }

    }

}
