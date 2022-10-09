package com.example.projectmusicadmin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectmusicadmin.databinding.ShowProductLayoutBinding

class ProductsAdapter(val productsList : ArrayList<Products>?, val context: Context)
    : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    inner class ViewHolder(view: View, val binding: ShowProductLayoutBinding) :
        RecyclerView.ViewHolder(view) {
        init {
            binding.btnEditProduct.setOnClickListener {
                val item = productsList!![adapterPosition]
                val contextView:Context = view.context
                val intent = Intent(contextView, Edit_product::class.java)
                intent.putExtra("EditId",item.product_id)
                intent.putExtra("EditName",item.product_name)
                intent.putExtra("EditDetail",item.product_detail)
                intent.putExtra("EditBrand",item.product_brand)
                intent.putExtra("EditPrice",item.product_price.toString())
                intent.putExtra("EditImg",item.product_img)
                intent.putExtra("EditNum",item.product_num.toString())
                contextView.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ShowProductLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false)
        return ViewHolder(binding.root,binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        binding.showproName.text = "Name: " +productsList!![position].product_name
        binding.showproBrand.text = "Brand: " +productsList!![position].product_brand
        val url = productsList!![position].product_img
        val imagePath = binding.showproImg
        Glide.with(context)
            .load(url)
            .into(imagePath)
    }

    override fun getItemCount(): Int {
        return  productsList!!.size
    }
}