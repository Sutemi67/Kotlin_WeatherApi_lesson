package com.example.weather.retrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

//посылаем POST-запрос на авторизацию
class ForecaAuthRequest(val user: String, val password: String)

//опишем класс для ответа, получаем токен для последующих запросов
class ForecaAuthResponse(@SerializedName("access_token") val token: String) {
    annotation class SerializedName(val value: String)
}

//опишем запрос в интерфейсе
interface ForecaApi {

    @POST("/authorize/token?expire_hours=-1")
    fun authenticate(@Body request: ForecaAuthRequest): Call<ForecaAuthResponse>
}

