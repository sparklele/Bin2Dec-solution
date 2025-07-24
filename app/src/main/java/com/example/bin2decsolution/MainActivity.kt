 package com.example.bin2decsolution

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bin2decsolution.databinding.ActivityMainBinding
import kotlin.math.pow

 class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBar.toolbar)
        supportActionBar?.title = ""
        if(savedInstanceState != null){
            binding.binaryET.setText(savedInstanceState.getString("binary"))
            binding.outputDecimal.text = savedInstanceState.getString("decimal")
        }
        binding.convertBT.setOnClickListener{
            val binary = binding.binaryET.text.toString()
            val validation = validateBinaryNumber(binary)
            if(validation[false] == null){
                binding.outputDecimal.text = binaryToDecimal(binary)
            } else {
                Log.d("MainActivity", validation[false].toString())
                Toast.makeText(this, validation[false], Toast.LENGTH_SHORT).show()
            }
        }
    }
     private fun validateBinaryNumber(binary: String): MutableMap<Boolean, String> {
         return if(binary.matches(Regex("[01]+"))){
             mutableMapOf(true to binary)
         } else
             mutableMapOf(false to "Invalid Binary Number")
     }

     private fun binaryToDecimal(binary: String):String{
         var result = 0.0
         var exp = 0.0
         for(digit in binary.reversed()){
             result += (digit.digitToInt()) * 2.0.pow(exp)
             exp++
         }
         return result.toInt().toString()
     }

     override fun onSaveInstanceState(outState: Bundle) {
         super.onSaveInstanceState(outState)
         outState.putString("binary", binding.binaryET.text.toString())
         outState.putString("decimal", binding.outputDecimal.text.toString())
     }
}