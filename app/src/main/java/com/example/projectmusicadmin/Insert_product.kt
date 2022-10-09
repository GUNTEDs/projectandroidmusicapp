package com.example.projectmusicadmin

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectmusicadmin.databinding.ActivityInsertProductBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Insert_product : AppCompatActivity() {
    private lateinit var bindingInsert : ActivityInsertProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingInsert = ActivityInsertProductBinding.inflate(layoutInflater)
        setContentView(bindingInsert.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home)
        { finish() }
        return super.onOptionsItemSelected(item)
    }

    fun addProduct(v: View) {
        val addId: String = "";
        val api = ProjectmusicAPI.create()
        api.insertProduct(
            addId,
            bindingInsert.addName.text.toString(),
            bindingInsert.addDetail.text.toString(),
            bindingInsert.addBrand.text.toString(),
            bindingInsert.addPrice.text.toString().toDouble(),
            bindingInsert.addImg.text.toString(),
            bindingInsert.addNum.text.toString().toInt()
        ).enqueue(object : Callback<Products> {
            override fun onResponse(
                call: Call<Products>,
                response: retrofit2.Response<Products>
            ) {
                if (response.isSuccessful()) {
                    Toast.makeText(applicationContext,"Successfully Inserted", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(applicationContext, "Error ", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Products>, t: Throwable) {
                Toast.makeText(applicationContext,"Error onFailure " + t.message,Toast.LENGTH_LONG).show()
            }
        })
    }

    fun resetProduct(v: View) {
        bindingInsert.addName.text?.clear()
        bindingInsert.addBrand.text?.clear()
        bindingInsert.addDetail.text?.clear()
        bindingInsert.addPrice.text?.clear()
        bindingInsert.addImg.text?.clear()
        bindingInsert.addNum.text?.clear()
    }
}