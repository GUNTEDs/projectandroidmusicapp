package com.example.projectmusicadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectmusicadmin.databinding.ActivityManageProductBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Manage_product : AppCompatActivity() {
    private lateinit var binding : ActivityManageProductBinding
    var productsList  = arrayListOf<Products>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManageProductBinding.inflate(layoutInflater)
        setContentView(binding.root )

        // Link to RecyclerView
        binding.recyclerView.adapter = ProductsAdapter(this.productsList, applicationContext)
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(binding.recyclerView.getContext(),
                DividerItemDecoration.VERTICAL)
        )
    }

    override fun onResume() {
        super.onResume()
        callProjectmusicData()
    }

    fun callProjectmusicData(){
        productsList.clear();
        val ProductsmusicAPI = ProjectmusicAPI.create();
        ProductsmusicAPI.retrieveProduct()
            .enqueue(object : Callback<List<Products>> {
                override fun onResponse(call: Call<List<Products>>, response: Response<List<Products>>) {
                    response.body()?.forEach {
                        productsList.add(Products(it.product_id,it.product_name,it.product_detail,
                            it.product_brand,it.product_price,it.product_img,it.product_num,it.product_del))
                    }
                    //// Set Data to RecyclerRecyclerView
                    binding.recyclerView.adapter = ProductsAdapter(productsList,applicationContext)
                    binding.txt1.text = "Product List : "+ productsList.size.toString()
                }
                override fun onFailure(call: Call<List<Products>>, t: Throwable) {
                    Toast.makeText(applicationContext,"Error onFailure " + t.message, Toast.LENGTH_LONG).show()
                }
            })
    }
    fun btnAddProduct(v: View) {
        val intent = Intent(applicationContext, Insert_product::class.java)
        startActivity(intent)
    }
    fun btnEditProduct(v: View) {
        val intent = Intent(applicationContext, Edit_product::class.java)

        startActivity(intent)
    }
}