package com.example.projectmusicadmin

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Products (
    @Expose
    @SerializedName("product_id") val product_id: String,

    @Expose
    @SerializedName("product_name") val product_name: String,

    @Expose
    @SerializedName("product_detail") val product_detail: String,

    @Expose
    @SerializedName("product_brand") val product_brand: String,

    @Expose
    @SerializedName("product_price") val product_price: Double,

    @Expose
    @SerializedName("product_img") val product_img: String,

    @Expose
    @SerializedName("product_num") val product_num: Int,

    @Expose
    @SerializedName("product_del") val product_del: String){}

data class loginClass(
    @Expose
    @SerializedName("username") val admin_username:String,

    @Expose
    @SerializedName("password") val admin_password:String){}

