package com.example.exercise_2_bmi_calculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    lateinit var weight:EditText
    lateinit var height:EditText
    lateinit var bmiVal:TextView
    lateinit var bmiSta:TextView
    lateinit var bmiImg:ImageView

    val formater = DecimalFormat("0.0")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weight = findViewById(R.id.editTextWeight)
        height = findViewById(R.id.editTextHeight)
        bmiSta = findViewById(R.id.textViewBMIStatus)
        bmiVal = findViewById(R.id.textViewBMIVal)
        bmiImg = findViewById(R.id.imageViewBMI)

        findViewById<Button>(R.id.btnCal).setOnClickListener {
            BMICal(it)
        }

        findViewById<Button>(R.id.btnClr).setOnClickListener {
            clear()
        }
    }

    fun BMICal(view:View){

        try{

        bmiVal.setText(formater.format(weight.text.toString().toDouble()/(height.text.toString().toDouble()/100 * height.text.toString().toDouble()/100)))

        bmiSta.setText(BMIStatus(weight.text.toString().toDouble()/(height.text.toString().toDouble()/100 * height.text.toString().toDouble()/100)))

        bmiImg.setImageResource(BMIImage(weight.text.toString().toDouble()/(height.text.toString().toDouble()/100 * height.text.toString().toDouble()/100)))
        }catch (e:Exception){
            clear()
        }

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0)

    }

    fun clear(){

        height.setText("")
        weight.setText("")
        bmiSta.setText("")
        bmiVal.setText("")
        bmiImg.setImageResource(R.drawable.empty)
    }

    private fun BMIImage(double: Double):Int{

        val bmiImgSr = when {
            double < 18.5 -> R.drawable.under
            (double >= 18.5 && double <= 24.9) -> R.drawable.normal
            double >= 25.0 -> R.drawable.over
            else -> R.drawable.empty
        }
        return bmiImgSr
    }

    private fun BMIStatus(double: Double):Int{

        val bmiSta = when {
            double < 18.5 -> R.string.under
            (double >= 18.5 && double <= 24.9) -> R.string.nor
            double >= 25.0 -> R.string.over
            else -> R.string.undefined
        }
        return bmiSta
    }
}
