package com.example.exercise_2_bmi_calculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    lateinit var weight:EditText
    lateinit var height:EditText
    lateinit var bmi:TextView
    lateinit var bmiImg:ImageView

    val formater = DecimalFormat("0.0")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weight = findViewById(R.id.editTextWeight)
        height = findViewById(R.id.editTextHeight)
        bmi = findViewById(R.id.textViewBMI)
        bmiImg = findViewById(R.id.imageViewBMI)

        findViewById<Button>(R.id.btnCal).setOnClickListener {
            BMICal(it)
        }

        findViewById<Button>(R.id.btnClr).setOnClickListener {
            clear()
        }
    }

    fun BMICal(view:View){

        bmi.setText(R.string.bmi_text)

          try{
              val totalBMI : Double = weight.text.toString().toDouble()/(height.text.toString().toDouble()/100 * height.text.toString().toDouble()/100)

              bmi.append(formater.format(totalBMI))
              bmi.append("  ")
              bmi.append(getResources().getString(BMIStatus(totalBMI)))
              bmiImg.setImageResource(BMIImage(totalBMI))

        }catch (e:Exception){
            clear()
            Toast.makeText(this, "Please don't leave Weight and Height empty",
                Toast.LENGTH_SHORT).show()
        }

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0)

    }

    fun clear(){

        height.setText("")
        weight.setText("")
        bmi.setText(R.string.bmi_text)
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
            else -> R.string.input_error
        }
        return bmiSta
    }
}
