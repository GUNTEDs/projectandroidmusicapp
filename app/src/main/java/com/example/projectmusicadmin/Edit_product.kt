package com.example.projectmusicadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.projectmusicadmin.databinding.ActivityEditProductBinding
import retrofit2.Call
import retrofit2.Callback

class Edit_product : AppCompatActivity() {
    private lateinit var bindingEdit : ActivityEditProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingEdit = ActivityEditProductBinding.inflate(layoutInflater)
        setContentView(bindingEdit.root)
        val editproId = intent.getStringExtra("EditId")
        val editproName = intent.getStringExtra("EditName")
        val editproDetail = intent.getStringExtra("EditDetail")
        val editproBrand = intent.getStringExtra("EditBrand")
        val editproPrice = intent.getStringExtra("EditPrice")
        val editproImg = intent.getStringExtra("EditImg")
        val editproNum = intent.getStringExtra("EditNum")

        bindingEdit.editId.setText(editproId)
        bindingEdit.editId.isEnabled = false
        bindingEdit.editName.setText(editproName)
        bindingEdit.editDetail.setText(editproDetail)
        bindingEdit.editBrand.setText(editproBrand)
        bindingEdit.editPrice.setText(editproPrice)
        bindingEdit.editImg.setText(editproImg)
        bindingEdit.editNum.setText(editproNum)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId== android.R.id.home)
        { finish() }
        return super.onOptionsItemSelected(item)
    }

    fun editProduct(v: View) {
        val api = ProjectmusicAPI.create()
        api.updateProduct(
            bindingEdit.editId.text.toString(),
            bindingEdit.editName.text.toString(),
            bindingEdit.editDetail.text.toString(),
            bindingEdit.editBrand.text.toString(),
            bindingEdit.editPrice.text.toString().toDouble(),
            bindingEdit.editImg.text.toString(),
            bindingEdit.editNum.text.toString().toInt()
        ).enqueue(object : Callback<Products> {
            override fun onResponse(
                call: Call<Products>,
                response: retrofit2.Response<Products>
            ) {
                if (response.isSuccessful()) {
                    Toast.makeText(applicationContext,"Successfully Updated", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(applicationContext, "Error ", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Products>, t: Throwable) {
                Toast.makeText(applicationContext,"Error onFailure " + t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
    fun delProduct(v: View){
        val api = ProjectmusicAPI.create()
        api.deleteProduct(
            bindingEdit.editId.text.toString()
        ).enqueue(object : Callback<Products> {
            override fun onResponse(
                call: Call<Products>,
                response: retrofit2.Response<Products>
            ) {
                if (response.isSuccessful()) {
                    Toast.makeText(applicationContext,"Successfully Deleted", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(applicationContext, "Error ", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Products>, t: Throwable) {
                Toast.makeText(applicationContext,"Error onFailure " + t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}