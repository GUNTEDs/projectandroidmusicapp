package com.example.projectmusicadmin

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ProjectmusicAPI {
    @GET("allproducts")
    fun retrieveProduct(): Call<List<Products>>

    @FormUrlEncoded
    @POST("addproducts")
    fun insertProduct(
        @Field("product_id") product_id: String,
        @Field("product_name") product_name: String,
        @Field("product_detail") product_detail: String,
        @Field("product_brand") product_brand: String,
        @Field("product_price") product_price: Double,
        @Field("product_img") product_img: String,
        @Field("product_num") product_num: Int,
    ): Call<Products>

    @FormUrlEncoded
    @PUT("updateproduct/{product_id}")
    fun updateProduct(
        @Path("product_id") product_id: String,
        @Field("product_name") product_name: String,
        @Field("product_detail") product_detail: String,
        @Field("product_brand") product_brand: String,
        @Field("product_price") product_price: Double,
        @Field("product_img") product_img: String,
        @Field("product_num") product_num: Int,
    ): Call<Products>

    @DELETE("deleteproduct/{product_id}")
    fun deleteProduct(
        @Path("product_id") product_id: String,
    ): Call<Products>

    @FormUrlEncoded
    @POST("adminlogin")
    fun loginUser(
        @Field("username") admin_username:String,
        @Field("password") admin_password:String,
    ): Call<List<loginClass>>

    companion object {
        fun create(): ProjectmusicAPI {
            val ProductsmusicAPI: ProjectmusicAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ProjectmusicAPI::class.java)
            return  ProductsmusicAPI;
        }
    }
}