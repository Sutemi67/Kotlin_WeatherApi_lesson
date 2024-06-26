package com.example.weather.retrofit

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

//посылаем POST-запрос на авторизацию
class ForecaAuthRequest(val user: String, val password: String)

//опишем класс для ответа, получаем токен для последующих запросов
class ForecaAuthResponse(@SerializedName("access_token") val token: String) {
}


//опишем запрос в интерфейсе
interface ForecaApi {
    @POST("/authorize/token?expire_hours=-1")
    fun authenticate(
        @Body request: ForecaAuthRequest
    ): Call<ForecaAuthResponse>

    @GET("/api/v1/location/search/{query}")
    fun getLocations(
        @Header("Authorization") token: String,
        @Path("query") query: String
    ): Call<LocationsResponse>

    @GET("/api/v1/current/{location}")
    fun getForecast(
        @Header("Authorization") token: String,
        @Path("location") locationId: Int
    ): Call<ForecastResponse>
}

//создадим дата класс для элемента списка
data class ForecastLocation(
    val id: Int,
    val name: String,
    val country: String
)

//теперь создадим класс для ответа на запрос
class LocationsResponse(val locations: ArrayList<ForecastLocation>)

data class CurrentWeather(
    val temperature: Float,
    val feelsLikeTemp: Float
)

class ForecastResponse(val current: CurrentWeather)


