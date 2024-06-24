package com.example.weather.retrofit

//посылаем POST-запрос на авторизацию
class ForecaAuthRequest(val user: String, val password: String)

//опишем класс для ответа, получаем токен для последующих запросов
class ForecaAuthResponse(@SerializedName("access_token") val token: String)