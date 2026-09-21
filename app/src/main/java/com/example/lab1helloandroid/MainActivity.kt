 package com.example.lab1helloandroid

import	android.os.Bundle
import	android.widget.Button
import	android.widget.TextView
import	androidx.appcompat.app.AppCompatActivity
class	MainActivity	:	AppCompatActivity()	{
    override	fun	onCreate(savedInstanceState:	Bundle?)	{
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var count = 0
        var step = 1

        val	message	=	findViewById<TextView>(R.id.textViewOutput)
        val	addButton	=	findViewById<Button>(R.id.buttonAdd)
        val subtractButton = findViewById<Button>(R.id.buttonSubtract)
        val resetButton = findViewById<Button>(R.id.buttonReset)
        val stepButton = findViewById<Button>(R.id.buttonStep)
        addButton.setOnClickListener	{
            count += step
            message.text = count.toString()
        }

        subtractButton.setOnClickListener {
            count -= step
            message.text = count.toString()
        }

        resetButton.setOnClickListener {
            count = 0
            step = 1
            message.text = getString(R.string._0)
        }
        stepButton.setOnClickListener {
            step = 2
        }
    }
}