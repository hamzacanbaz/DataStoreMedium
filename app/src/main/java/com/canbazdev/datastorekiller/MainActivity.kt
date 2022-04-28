package com.canbazdev.datastorekiller

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.canbazdev.datastorekiller.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewmodel: DataStoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel.name.observe(this) {
            binding.etUserName.setText(it.toString())
        }

        viewmodel.age.observe(this) {
            binding.etUserAge.setText(it.toString())
        }

        binding.btnSaveData.setOnClickListener {
            viewmodel.setName(binding.etUserName.text.toString())
            viewmodel.setAge(binding.etUserAge.text.toString().toInt())
            Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()
        }

    }

}