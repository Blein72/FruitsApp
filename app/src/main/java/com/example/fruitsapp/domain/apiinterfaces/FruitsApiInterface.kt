package com.example.fruitsapp.domain.apiinterfaces

import com.example.fruitsapp.domain.model.FruitDto
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface FruitsApiInterface {
    @GET("http://fruits.dev/api/fruits")
    fun getFruitsList(): Observable<List<FruitDto>>

    @GET("http://fruits.dev/api/fruit/{id}")
    fun getFruitById(@Path("id") id: String): Observable<FruitDto>

    @POST("http://fruits.dev/api/fruits")
    fun addFruit(@Field("name") name: String,
                 @Field("weight") weight: Long,
                 @Field("delicious") delicious: Boolean,
                 @Field("color") color: String): Observable<FruitDto>
}