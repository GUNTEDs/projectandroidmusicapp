package com.example.projectmusicadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.projectmusicadmin.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    fun clickLogin(v: View){
        val musicClient = ProjectmusicAPI.create()
        musicClient.loginUser(
            binding.etUsername.text.toString(),
            binding.etPassword.text.toString()
        ).enqueue(object: Callback<List<loginClass>>{
            override fun onFailure(call: Call<List<loginClass>>, t: Throwable) {
                Toast.makeText(applicationContext,"Wrong username or password" , Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<List<loginClass>>, response: Response<List<loginClass>>) {
                if (response.isSuccessful){
                    val intent = Intent(applicationContext, Manage_product::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(applicationContext, response.message(), Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}